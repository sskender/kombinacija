package hr.fer.opp.services;

import hr.fer.opp.model.Container;
import hr.fer.opp.model.Person;

import java.util.List;

public interface EmployeeService {

    /**
     * @param containerId
     * @param worker
     * @return
     */
    boolean emptyContainer(Long containerId, Person worker);

    /**
     * @param worker
     * @return
     */
    List<Container> getWorkRoute(Person worker);

}
