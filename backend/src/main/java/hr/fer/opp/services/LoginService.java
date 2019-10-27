package hr.fer.opp.services;


import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.model.Admin;
import hr.fer.opp.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Checks if a citizen logging in is a citizen or admin
     *
     * @param citizen
     * @return
     */
    @Transactional
    public String checkUser(Citizen citizen) {
        if(isAdmin(citizen.getEmail())) {
            return "admin";
        }
        if(isUser((citizen.getEmail()))) {
            return "citizen";
        }
        return "Error, no citizen with given email.";
    }

    /**
     * Checks if there is an account with the given e-mail
     * in the admin table
     * @param email the User received from the front-end
     * @return
     */
    public boolean isAdmin(String email){
        Optional<Admin> opt = adminRepository.findByEmail(email);
        return opt.isPresent();
    }

    /**
     * Checks if there is an account with the given e-mail
     * in the citizens table
     * @param email the User received from the front-end
     * @return
     */
    public boolean isUser(String email) {
        Optional<Citizen> opt = citizenRepository.findByEmail(email);
        return opt.isPresent();
    }

    /**
     * Registers citizen in the citzens table
     * Admins can only be manually inserted
     *
     * @param citizen Registered citizen
     * @return
     */
    @Transactional
    public String registerUser(Citizen citizen) {
        if(isAdmin(citizen.getEmail()) || isUser(citizen.getEmail())) {
            return "User with this email already exists!";
        }

        citizen.setReputation(0);

        if (citizenRepository.insertUser(citizen)) {
            return "User successfully registered!";
        } else {
            return "Error, could not register citizen!";
        }
    }

}
