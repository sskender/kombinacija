package hr.fer.opp.services.impl;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.model.Person;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Person fetchByEmail(String email) {
        Optional<Person> o = personRepository.findByEmail(email);
        if(o.isPresent()){
            return o.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean isAdmin(Long id) {
        return adminRepository.findById(id).isPresent();
    }

    @Override
    public boolean isEmployee(Long id) {
        return employeeRepository.findById(id).isPresent();
    }

    @Override
    public boolean isCitizen(Long id) {
        return citizenRepository.findById(id).isPresent();
    }

}
