package hr.fer.opp.services;

import hr.fer.opp.model.Container;
import hr.fer.opp.model.Emptying;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.Ping;

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

    /**
     * Get list of pings which have been made after the last
     * emptying of a given Container
     * @param container
     * @return
     */
    List<Ping> getPingsSinceLastEmptying(Container container);
}
