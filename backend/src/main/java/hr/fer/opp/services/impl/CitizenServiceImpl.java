package hr.fer.opp.services.impl;

import hr.fer.opp.model.Favorite;
import hr.fer.opp.model.Ping;
import hr.fer.opp.services.CitizenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Override
    public Favorite addToFavorites(Long containerId) {
        return null;
    }

    @Override
    public boolean removeFromFavorites(Long containerId) {
        return false;
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) {
        return null;
    }

    @Override
    public List<Favorite> getFavoriteContainers() {
        return null;
    }

    @Override
    public Ping pingEmpty(Long containerId) {
        return null;
    }

    @Override
    public Ping pingFull(Long containerId) {
        return null;
    }

    @Override
    public Ping pingUrgent(Long containerId) {
        return null;
    }

}
