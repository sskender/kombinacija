package hr.fer.opp.services.impl;

import hr.fer.opp.model.Person;
import hr.fer.opp.services.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean confirmContainerEmptied(Long containerId, Person worker) {
        return false;
    }

}
