package hr.fer.opp.services;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    CitizenRepository citizenRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AdminRepository adminRepository;

    public Person fetchByEmail(String email){
        return personRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException());
    }

    public boolean isAdmin(Long id){
        return adminRepository.findById(id).isPresent();
    }

    public boolean isEmployee(Long id){
        return employeeRepository.findById(id).isPresent();
    }

    public boolean isCitizen(Long id){
        return citizenRepository.findById(id).isPresent();
    }
}
