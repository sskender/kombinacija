package hr.fer.opp.controllers;

import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value="/register")
    public Citizen registerUser(@RequestBody RegisterDTO registerDTO) {
        registerDTO.setPwd(new BCryptPasswordEncoder().encode(registerDTO.getPwd()));
        return loginService.registerUser(registerDTO);
    }
}
