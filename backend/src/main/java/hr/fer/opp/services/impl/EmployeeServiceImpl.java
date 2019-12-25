package hr.fer.opp.services.impl;

import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.EmptyingRepository;
import hr.fer.opp.dao.PingRepository;
import hr.fer.opp.exceptions.ExceptionMessages;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.*;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.model.enums.RouteStatus;
import hr.fer.opp.services.CitizenService;
import hr.fer.opp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private EmptyingRepository emptyingRepository;

    @Autowired
    private PingRepository pingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CitizenService citizenService;

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
        pingList.stream()
                .filter(ping -> ping.getLevel().equals(PingLevel.FULL) || ping.getLevel().equals(PingLevel.URGENT))
                .map(Ping::getCreator)
                .forEach(person -> citizenService.increaseReputation(person));

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
        pingList.stream()
                .filter(ping -> ping.getLevel().equals(PingLevel.FULL) || ping.getLevel().equals(PingLevel.URGENT))
                .map(Ping::getCreator)
                .forEach(person -> citizenService.decreaseReputation(person));

        return true;
    }

    @Override
    public List<Container> getWorkRoute(Person worker) {
        return null;
    }

}
