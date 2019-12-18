package hr.fer.opp.services.impl;

import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.exceptions.ExceptionMessages;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Emptying;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.enums.RouteStatus;
import hr.fer.opp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
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
        containerOptional.get().setPingsSinceEmptied(0);
        containerOptional.get().setRouteStatus(RouteStatus.PENDING);
        containerOptional.get().getEmptyings().add(emptying);

        // update worker
        employeeOptional.get().getEmptyings().add(emptying);

        return emptying;
    }

    @Override
    public boolean reportLegitimatePing(Long containerId) {
        return false;
    }

    @Override
    public boolean reportFakePing(Long containerId) {
        return false;
    }

    @Override
    public List<Container> getWorkRoute(Person worker) {
        return null;
    }

}
