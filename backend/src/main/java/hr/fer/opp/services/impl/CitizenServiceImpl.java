package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.FavoriteRepository;
import hr.fer.opp.dao.PingRepository;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.*;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PingRepository pingRepository;

    @Override
    @Transactional
    public int increaseReputation(Citizen citizen) {
        citizen.setReputation(citizen.getReputation() + Citizen.CITIZEN_REPUTATION_INCREASE);
        return citizenRepository.save(citizen).getReputation();
    }

    @Override
    @Transactional
    public int decreaseReputation(Citizen citizen) {
        citizen.setReputation(citizen.getReputation() - Citizen.CITIZEN_REPUTATION_DECREASE);
        if (citizen.getReputation() < 0) {
            citizen.setReputation(Citizen.DEFAULT_CITIZEN_REPUTATION);
        }
        return citizenRepository.save(citizen).getReputation();
    }

    @Override
    @Transactional
    public Favorite addToFavorites(Long containerId, Person owner) {
        Optional<Container> containerOptional = containerRepository.findById(containerId);

        if (!containerOptional.isPresent()) {
            throw new RequestDeniedException("Can't register given container as a favorite. Container with the given id does not exist.");
        }

        Favorite favorite = new Favorite();
        favorite.setContainer(containerOptional.get());
        favorite.setOwner(owner);

        containerOptional.get().getFavorites().add(favorite);
        owner.getFavorites().add(favorite);

        return favoriteRepository.save(favorite);
    }

    @Override
    @Transactional
    public boolean removeFromFavorites(Long containerId, Person owner) {
        Optional<Container> containerOptional = containerRepository.findById(containerId);

        if (!containerOptional.isPresent()) {
            throw new RequestDeniedException("Container with given ID does not exist.");
        }

        List<Favorite> favorites = owner.getFavorites();

        for (Favorite favorite : favorites) {
            if (favorite.getContainer().getId().equals(containerOptional.get().getId())) {
                favoriteRepository.delete(favorite);
                return true;
            }
        }
        throw new RequestDeniedException("Favorite does not exist.");
    }

    @Override
    public List<Favorite> getFavoriteContainers(Person owner) {
        return favoriteRepository.findAll().stream().filter(f -> f.getOwner().getId().equals(owner.getId())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ping pingContainer(Long containerId, Person creator, PingLevel pingLevel) {
        // fetch container by id
        Optional<Container> containerOptional = containerRepository.findById(containerId);

        if (!containerOptional.isPresent()) {
            throw new RequestDeniedException("Container with given ID does not exist.");
        }

        Container container = containerOptional.get();

        // create ping
        Ping ping = new Ping();
        ping.setCreator(creator);
        ping.setContainer(container);
        ping.setLevel(pingLevel);
        ping.setTimestamp(System.currentTimeMillis());
        ping.setPhotoPath(Ping.DEFAULT_PHOTO_PATH);

        // update person
        creator.getPings().add(ping);

        // update container
        container.getPings().add(ping);
        container.setPingsSinceEmptied(container.getPingsSinceEmptied() + 1);

        return pingRepository.save(ping);
    }

}
