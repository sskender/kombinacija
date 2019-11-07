package hr.fer.opp.services;


import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Admin;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PersonRepository personRepository;


    /**
     * Registers citizen in the citzens table
     * Admins can only be manually inserted
     *
     * @param registerDTO Registered citizen
     * @return
     */
    @Transactional
    public Citizen registerUser(RegisterDTO registerDTO) {
        if(personRepository.findByEmail(registerDTO.getEmail()).isPresent()){
            throw new RequestDeniedException("User with given e-mail already exists.");
        }
        Citizen c = translate(registerDTO);
        return citizenRepository.save(c);
    }

    private Citizen translate(RegisterDTO registerDTO) {
        Citizen c = new Citizen();
        c.setEmail(registerDTO.getEmail());
        c.setPwdHash(registerDTO.getPwd());
        c.setName(registerDTO.getName());
        c.setLastName(registerDTO.getLastName());
        c.setReputation(0);

        c.setPings(new ArrayList<>());
        c.setFavorites(new ArrayList<>());

        return c;
    }
}
