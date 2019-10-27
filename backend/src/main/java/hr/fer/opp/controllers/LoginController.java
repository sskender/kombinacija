package hr.fer.opp.controllers;

import hr.fer.opp.model.Citizen;
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
    public String loginUser(@RequestBody Citizen citizen) {
        return loginService.checkUser(citizen);
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestBody Citizen citizen) {
        return loginService.registerUser(citizen);
    }

}
