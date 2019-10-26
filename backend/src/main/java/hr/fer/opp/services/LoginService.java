package hr.fer.opp.services;


import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.dao.UserRepository;
import hr.fer.opp.model.Admin;
import hr.fer.opp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Checks if a user logging in is a user or admin
     *
     * @param user
     * @return
     */
    @Transactional
    public String checkUser(User user) {
        if(isAdmin(user.getEmail())) {
            return "admin";
        }
        if(isUser((user.getEmail()))) {
            return "user";
        }
        return "Error, no user with given email.";
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
     * in the user table
     * @param email the User received from the front-end
     * @return
     */
    public boolean isUser(String email) {
        Optional<User> opt = userRepository.findByEmail(email);
        return opt.isPresent();
    }

    /**
     * Registers user in the users table
     * Admins can only be manually inserted
     *
     * @param user Registered user
     * @return
     */
    @Transactional
    public String registerUser(User user) {
        if(isAdmin(user.getEmail()) || isUser(user.getEmail())) {
            return "User with this email already exists!";
        }

        user.setReputation(0);

        if (userRepository.insertUser(user)) {
            return "User successfully registered!";
        } else {
            return "Error, could not register user!";
        }
    }

}
