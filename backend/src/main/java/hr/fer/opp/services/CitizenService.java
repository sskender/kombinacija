package hr.fer.opp.services;

import hr.fer.opp.model.Favorite;
import hr.fer.opp.model.Ping;

import java.util.List;

public interface CitizenService {

    Favorite addToFavorites(Long containerId);

    boolean removeFromFavorites(Long containerId);

    Favorite getFavoriteById(Long favoriteId);

    List<Favorite> getFavoriteContainers();


    Ping pingEmpty(Long containerId);

    Ping pingFull(Long containerId);

    Ping pingUrgent(Long containerId);

}
