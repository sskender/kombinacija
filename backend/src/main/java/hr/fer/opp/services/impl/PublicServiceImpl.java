package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PublicServiceImpl implements PublicService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional
    public Citizen registerCitizen(RegisterDTO registerDTO) {
        if (personRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new RequestDeniedException("User with given e-mail already exists.");
        }
        Citizen c = translate(registerDTO);
        return citizenRepository.save(c);
    }

    private Citizen translate(RegisterDTO registerDTO) {
        Citizen c = new Citizen();

        c.setEmail(registerDTO.getEmail());
        c.setPwdHash(encoder.encode(registerDTO.getPwd()));

        c.setName(registerDTO.getName());
        c.setLastName(registerDTO.getLastName());
        c.setReputation(0);

        c.setPings(new ArrayList<>());
        c.setFavorites(new ArrayList<>());

        return c;
    }

}
