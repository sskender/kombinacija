package hr.fer.opp.services.impl;

import hr.fer.opp.model.Favorite;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.Ping;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.services.CitizenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Override
    public Favorite addToFavorites(Long containerId, Person owner) {
        return null;
    }

    @Override
    public boolean removeFromFavorites(Long containerId, Person owner) {
        return false;
    }

    @Override
    public List<Favorite> getFavoriteContainers(Person owner) {
        return null;
    }

    @Override
    public Ping pingContainer(Long containerId, Person creator, PingLevel pingLevel) {
        return null;
    }

}
