package hr.fer.opp.services.impl;

import hr.fer.opp.model.Container;
import hr.fer.opp.model.Person;
import hr.fer.opp.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean emptyContainer(Long containerId, Person worker) {
        return false;
    }

    @Override
    public List<Container> getWorkRoute(Person worker) {
        return null;
    }

}
