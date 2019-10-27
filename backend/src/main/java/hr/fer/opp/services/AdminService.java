package hr.fer.opp.services;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

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
}
