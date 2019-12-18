package hr.fer.opp.services.impl;

import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.EmptyingREST;
import hr.fer.opp.model.Person;
import hr.fer.opp.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmptyingREST emptyContainer(Long containerId, Person worker) {
        return null;
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
    public List<ContainerREST> getWorkRoute(Person worker) {
        return null;
    }

}
