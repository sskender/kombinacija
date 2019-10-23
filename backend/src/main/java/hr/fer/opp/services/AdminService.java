package hr.fer.opp.services;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.model.Admin;
import hr.fer.opp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean isAdmin(User u){
        String email = u.getEmail();
        Optional<Admin> opt = adminRepository.findByEmail(email);
        return opt.isPresent();
    }
}
