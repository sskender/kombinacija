package hr.fer.opp.services.impl;

import hr.fer.opp.dao.*;
import hr.fer.opp.exceptions.ExceptionMessages;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.*;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.model.enums.RouteStatus;
import hr.fer.opp.services.AdminService;
import hr.fer.opp.services.CitizenService;
import hr.fer.opp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int DEFAULT_NECESSARY_PINGS = 2;
    private static final int DEFAULT_ROUTE_CAPACITY = 10;

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private EmptyingRepository emptyingRepository;

    @Autowired
    private PingRepository pingRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private AdminService adminService;

    @Override
    @Transactional
    public Emptying emptyContainer(Long containerId, Person worker) {
        // get container
        Optional<Container> containerOptional = containerRepository.findById(containerId);
        if (!containerOptional.isPresent()) {
            throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
        }

        // get employee
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(worker.getEmail());
        if (!employeeOptional.isPresent()) {
            throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_NOT_EXIST);
        }

        // new emptying object
        Emptying emptying = new Emptying();
        emptying.setContainer(containerOptional.get());
        emptying.setWorker(employeeOptional.get());
        emptying.setTimestamp(System.currentTimeMillis());

        // update container
        containerOptional.get().setPingsSinceEmptied(Container.DEFAULT_PINGS_SINCE_EMPTIED);
        containerOptional.get().setRouteStatus(RouteStatus.PENDING);
        containerOptional.get().getEmptyings().add(emptying);

        // update worker
        employeeOptional.get().getEmptyings().add(emptying);

        // save emptying record
        return emptyingRepository.save(emptying);
    }

    @Override
    public boolean reportLegitimatePing(Long containerId) {
        // select time of latest (newest) emptying for this container
        List<Emptying> emptyingList = emptyingRepository.findByContainer_IdOrderByTimestampDesc(containerId);

        // craft list of pings
        List<Ping> pingList;

        if (emptyingList.isEmpty()) {
            // select * from pings where container id ?
            pingList = pingRepository.findByContainer_Id(containerId);

        } else {
            // grab oldest timestamp
            long timestamp = emptyingList.get(0).getTimestamp();

            // select * from pings where container id ?
            //                      and timestamp > ?
            pingList = pingRepository.findByContainer_IdAndTimestampGreaterThan(containerId, timestamp);
        }

        // filter through ping list and increase reputation for legit pings
        List<Person> processedPersonList = new ArrayList<>();

        pingList.stream()
                .filter(ping -> ping.getLevel().equals(PingLevel.FULL) || ping.getLevel().equals(PingLevel.URGENT))
                .map(Ping::getCreator)
                .forEach(person -> {
                    if (!processedPersonList.contains(person)) {
                        citizenService.increaseReputation(person);
                        processedPersonList.add(person);
                    }
                });

        return true;
    }

    @Override
    public boolean reportFakePing(Long containerId) {
        // select time of latest (newest) emptying for this container
        List<Emptying> emptyingList = emptyingRepository.findByContainer_IdOrderByTimestampDesc(containerId);

        // craft list of pings
        List<Ping> pingList;

        if (emptyingList.isEmpty()) {
            // select * from pings where container id ?
            pingList = pingRepository.findByContainer_Id(containerId);

        } else {
            // grab oldest timestamp
            long timestamp = emptyingList.get(0).getTimestamp();

            // select * from pings where container id ?
            //                      and timestamp > ?
            pingList = pingRepository.findByContainer_IdAndTimestampGreaterThan(containerId, timestamp);
        }

        // filter through ping list and decrease reputation for fake pings
        List<Person> processedPersonList = new ArrayList<>();

        pingList.stream()
                .filter(ping -> ping.getLevel().equals(PingLevel.FULL) || ping.getLevel().equals(PingLevel.URGENT))
                .map(Ping::getCreator)
                .forEach(person -> {
                    if (!processedPersonList.contains(person)) {
                        citizenService.decreaseReputation(person);
                        processedPersonList.add(person);
                    }
                });

        return true;
    }

    @Override
    public synchronized List<Container> getWorkRoute(Person worker) {
        Long neighborhoodId = employeeRepository.getOne(worker.getId()).getNeighborhood().getId();
        List<Container> all = adminService.getContainersByNeighborhoodId(neighborhoodId)
                .stream()
                .filter(c -> c.getRouteStatus().equals(RouteStatus.PENDING) && c.getPingsSinceEmptied() >= DEFAULT_NECESSARY_PINGS)
                .collect(Collectors.toList());
        if(all.isEmpty()){
            return all;
        }
        Map<Container, Double> map = new HashMap<>();
        Iterator<Container> iter = all.iterator();
        while(iter.hasNext()) {
            Container c = iter.next();
            List<Ping> pingsSinceLatest = getPingsSinceLastEmptying(c);
            double avg = computeAverage(pingsSinceLatest);
            if (avg == 0.) {
                //removing containers which are thought to empty due to being
                //marked as such by admins/employees or a sufficient number
                //of citizens
                iter.remove();
                continue;
            }
            map.put(c, avg);
        }

        if(all.isEmpty()){
            return all;
        }

        double usedSpace = 0.;
        List<Container> route = new ArrayList<>();
        route.add(all.get(0));
        usedSpace += map.get(all.get(0));
        all.remove(0);
        while(usedSpace < DEFAULT_ROUTE_CAPACITY && !all.isEmpty()){
            Container lastAdded = route.get(route.size()-1);
            Container closest = findClosestContainer(lastAdded, all);
            route.add(closest);
            all.remove(closest);
            usedSpace += map.get(closest);
        }

        return route;
    }

    private Container findClosestContainer(Container lastAdded, List<Container> all) {
        Container closest = all.get(0);
        double d = distance(lastAdded, all.get(0));
        for(Container c : all){
            double temp = distance(lastAdded, c);
            if(temp < d){
                d = temp;
                closest = c;
            }
        }
        return closest;
    }

    private double distance(Container a, Container b){
        double xDiff = a.getLatitude()-b.getLatitude();
        double yDiff = a.getLongitude()-b.getLongitude();
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }

    private Double computeAverage(List<Ping> pingsSinceLatest) {
        double sum = 0.;
        int fullCounter = 0;
        int emptyCounter = 0;
        for(Ping p : pingsSinceLatest){
            if(p.getLevel().equals(PingLevel.FULL)){
                sum += 1.;
                fullCounter++;
            } else if(p.getLevel().equals(PingLevel.URGENT)){
                sum += 1.5;
                fullCounter++;
            } else if(p.getLevel().equals(PingLevel.EMPTY)){
                Long creatorId = p.getCreator().getId();
                //if employee/admin marked as empty, we take their word as true
                if(employeeRepository.existsById(creatorId) || adminRepository.existsById(creatorId)){
                    return 0.;
                //otherwise, a citizen marked it as empty, which does not have to be true
                } else {
                    emptyCounter++;
                }
            }
        }
        if(emptyCounter>fullCounter){
            return 0.;
        }
        return sum / (double)fullCounter;

    }

    private List<Ping> getPingsSinceLastEmptying(Container c) {
        //extract timestamp of latest emptying
        long ts = 0;
        for(Emptying e : c.getEmptyings()){
            ts = e.getTimestamp()>ts ? e.getTimestamp() : ts;
        }
        //extract all pings made since the last emptying
        List<Ping> pings = new ArrayList<>();
        for(Ping p : c.getPings()){
            if(p.getTimestamp() > ts){
                pings.add(p);
            }
        }
        return pings;
    }
}
