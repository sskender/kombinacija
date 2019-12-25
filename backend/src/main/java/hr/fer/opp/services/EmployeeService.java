package hr.fer.opp.services;

import hr.fer.opp.model.Container;
import hr.fer.opp.model.Emptying;
import hr.fer.opp.model.Person;

import java.util.List;

public interface EmployeeService {

    /**
     * Empty full container.
     *
     * @param containerId
     * @param worker
     * @return
     */
    Emptying emptyContainer(Long containerId, Person worker);

    /**
     * Increase citizen's reputation for legitimate ping.
     *
     * @param containerId
     * @return
     */
    boolean reportLegitimatePing(Long containerId);

    /**
     * Decrease citizen's reputation for fake ping.
     *
     * @param containerId
     * @return
     */
    boolean reportFakePing(Long containerId);

    /**
     * Get list of containers to clean in upcoming route.
     *
     * @param worker
     * @return
     */
    List<Container> getWorkRoute(Person worker);

}
