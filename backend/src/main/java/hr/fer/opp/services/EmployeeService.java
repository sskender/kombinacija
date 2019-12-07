package hr.fer.opp.services;

import hr.fer.opp.model.Person;

public interface EmployeeService {

    boolean confirmContainerEmptied(Long containerId, Person worker);

}
