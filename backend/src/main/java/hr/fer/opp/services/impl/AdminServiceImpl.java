package hr.fer.opp.services.impl;

import hr.fer.opp.dao.AdminRepository;
import hr.fer.opp.model.Admin;
import hr.fer.opp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean isAdmin(String email) {
        Optional<Admin> opt = adminRepository.findByEmail(email);
        return opt.isPresent();
    }

}
