package hr.fer.opp.controllers;

import hr.fer.opp.dto.LoginDTO;
import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value="/login")
    public String loginUser(@RequestBody LoginDTO loginDTO) {
        // user hash password
        // return loginService.checkUser(citizen);
        return null;
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO) {
        //return loginService.registerUser(citizen);
        return null;
    }

}
