package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.FavoriteRepository;
import hr.fer.opp.dao.PingRepository;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.*;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CitizenServiceImplTest {

    @InjectMocks
    CitizenServiceImpl citizenService;

    @Mock
    CitizenRepository citizenRepository;

    @Mock
    ContainerRepository containerRepository;

    @Mock
    FavoriteRepository favoriteRepository;

    @Mock
    EmployeeService employeeService;

    @Mock
    PingRepository pingRepository;


    @Test
    public void increaseReputationNeutral() {
        Person p = new Person();
        p.setEmail("pero.peric@fer.hr");

        Citizen c = new Citizen();
        c.setReputation(0);

        Mockito.doReturn(Optional.of(c)).when(citizenRepository).findByEmail("pero.peric@fer.hr");

        Mockito.when(citizenRepository.save(c)).thenReturn(c);

        citizenService.increaseReputation(p);

        Assert.assertEquals(10, (long)c.getReputation());
    }

    @Test (expected = RequestDeniedException.class)
    public void increaseReputationException() {
        Person p = new Person();
        p.setEmail("pero.peric@fer.hr");

        Citizen c = new Citizen();
        c.setReputation(-156);

        citizenService.increaseReputation(p);
    }

    @Test
    public void decreaseReputationToZero() {
        Person p = new Person();
        p.setEmail("pero.peric@fer.hr");

        Citizen c = new Citizen();
        c.setReputation(5);

        Mockito.doReturn(Optional.of(c)).when(citizenRepository).findByEmail("pero.peric@fer.hr");

        Mockito.when(citizenRepository.save(c)).thenReturn(c);

        citizenService.decreaseReputation(p);

        Assert.assertEquals(0, (long)c.getReputation());
    }

    @Test
    public void decreaseReputationNeutral() {
        Person p = new Person();
        p.setEmail("pero.peric@fer.hr");

        Citizen c = new Citizen();
        c.setReputation(40);

        Mockito.doReturn(Optional.of(c)).when(citizenRepository).findByEmail("pero.peric@fer.hr");

        Mockito.when(citizenRepository.save(c)).thenReturn(c);

        citizenService.decreaseReputation(p);

        Assert.assertEquals(25, (long)c.getReputation());
    }


    @Test (expected = RequestDeniedException.class)
    public void decreaseReputationException() {
        Person p = new Person();
        p.setEmail("pero.peric@fer.hr");

        Citizen c = new Citizen();
        c.setReputation(40);

        citizenService.decreaseReputation(p);
    }


    @Test (expected = RequestDeniedException.class)
    public void addToFavoritesException() {
        Container c = new Container();
        c.setId(15L);

        Person p = new Person();
        p.setId(12L);

        Favorite f = new Favorite();
        f.setOwner(p);
        f.setContainer(c);

        List<Favorite> favorites = new ArrayList<>();
        favorites.add(f);

        citizenService.addToFavorites(12L,p);
    }

    @Test
    public void getFavoriteContainers() {
        Person p = new Person();
        p.setId(12L);

        Favorite f1 = new Favorite();
        Favorite f2 = new Favorite();

        List<Favorite> expectedFavorites = new ArrayList<>();
        expectedFavorites.add(f1);
        expectedFavorites.add(f2);

        Mockito.doReturn(expectedFavorites).when(favoriteRepository).findByOwner_Id(12L);

        List<Favorite> actualFavorites = citizenService.getFavoriteContainers(p);

        assertThat(actualFavorites).isEqualTo(expectedFavorites);
    }

}
