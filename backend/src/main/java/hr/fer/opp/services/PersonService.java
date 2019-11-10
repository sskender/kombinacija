package hr.fer.opp.services;

import hr.fer.opp.model.Person;

public interface PersonService {

    Person fetchByEmail(String email);

    boolean isAdmin(Long id);

    boolean isEmployee(Long id);

    boolean isCitizen(Long id);

}
