package hr.fer.opp.services;

import hr.fer.opp.model.Favorite;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.Ping;
import hr.fer.opp.model.enums.PingLevel;

import java.util.List;

public interface CitizenService {

    /**
     * Mark container as favorite.
     *
     * @param containerId
     * @param owner
     * @return
     */
    Favorite addToFavorites(Long containerId, Person owner);

    /**
     * Remove container from list of favorite containers.
     *
     * @param containerId
     * @param owner
     * @return
     */
    boolean removeFromFavorites(Long containerId, Person owner);

    /**
     * Get a list of all containers marked as favorite.
     *
     * @param owner
     * @return
     */
    List<Favorite> getFavoriteContainers(Person owner);


    /**
     * Ping container action.
     *
     * @param containerId
     * @param creator
     * @param pingLevel
     * @return
     */
    Ping pingContainer(Long containerId, Person creator, PingLevel pingLevel);

    default Ping pingContainerEmpty(Long containerId, Person creator) {
        return pingContainer(containerId, creator, PingLevel.EMPTY);
    }

    default Ping pingContainerFull(Long containerId, Person creator) {
        return pingContainer(containerId, creator, PingLevel.FULL);
    }

    default Ping pingContainerUrgent(Long containerId, Person creator) {
        return pingContainer(containerId, creator, PingLevel.URGENT);
    }

}
