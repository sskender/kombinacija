package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CitizenServiceImplTest {

    @InjectMocks
    CitizenServiceImpl citizenService;

    @Mock
    CitizenRepository citizenRepository;


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

        Mockito.doReturn(Optional.of(c)).when(citizenRepository).findByEmail("ivan.ivic@fer.hr");

        Mockito.when(citizenRepository.save(c)).thenReturn(c);

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

        Mockito.doReturn(Optional.of(c)).when(citizenRepository).findByEmail("ivan.ivic@fer.hr");

        Mockito.when(citizenRepository.save(c)).thenReturn(c);

        citizenService.decreaseReputation(p);
    }
}
