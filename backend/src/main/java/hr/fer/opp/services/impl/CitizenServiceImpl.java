package hr.fer.opp.services.impl;

import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.FavoriteRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.dao.PingRepository;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Favorite;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.Ping;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PersonRepository personRepository;

    @Autowired
    private PingRepository pingRepository;

    @Override
    public Favorite addToFavorites(Long containerId, Person owner) {
        Favorite f = new Favorite();

        Optional<Container> c = containerRepository.findById(containerId);

        if(c.isPresent()) {
            f.setContainer(c.get());
        } else {
            throw new RequestDeniedException("Can't register given container as a favorite. Container with the given id does not exist.");
        }

        Optional<Person> p = personRepository.findById(owner.getId());

        if(p.isPresent()) {
            f.setOwner(p.get());
        } else {
            throw new RequestDeniedException("Person with the given id does not exist.");
        }

        c.get().getFavorites().add(f);
        p.get().getFavorites().add(f);

        return favoriteRepository.save(f);
    }

    @Override
    public boolean removeFromFavorites(Long containerId, Person owner) {
        Optional<Container> c = containerRepository.findById(containerId);
        Optional<Person> p = personRepository.findById(owner.getId());


        if (!c.isPresent()) {
            throw new RequestDeniedException("Container with given ID does not exist.");
        }

        if (!p.isPresent()) {
            throw new RequestDeniedException("Person with given ID does not exist.");
        }

        List<Favorite> favorites = p.get().getFavorites();

        for(Favorite f : favorites) {
            if (f.getContainer().getId() == c.get().getId()) {
                favoriteRepository.delete(f);
                return true;
            }
        }
        throw new RequestDeniedException("Favorite does not exist.");
    }

    @Override
    public List<Favorite> getFavoriteContainers(Person owner) {
        Optional<Person> p = personRepository.findById(owner.getId());

        if(p.isPresent()) {
            return favoriteRepository.findAll().stream().filter(f -> f.getOwner().getId() == p.get().getId()).collect(Collectors.toList());
        } else {
            throw new RequestDeniedException("Person with given Id does not exist.");
        }
    }

    @Override
    public Ping pingContainer(Long containerId, Person creator, PingLevel pingLevel) {
        Optional<Container> c = containerRepository.findById(containerId);
        Optional<Person> p = personRepository.findById(creator.getId());
        Ping ping = new Ping();

        if (p.isPresent()) {
            ping.setCreator(p.get());
        } else {
            throw new RequestDeniedException("Person with given ID does not exist.");
        }

        if (c.isPresent()) {
            ping.setContainer(c.get());
        } else {
            throw new RequestDeniedException("Container with given ID does not exist.");
        }


        ping.setLevel(pingLevel);
        ping.setTimestamp(0);
        ping.setPhotoPath("");

        p.get().getPings().add(ping);
        c.get().getPings().add(ping);

        return pingRepository.save(ping);
    }

}
