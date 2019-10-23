package hr.fer.opp.controllers;

import hr.fer.opp.model.Admin;
import hr.fer.opp.model.User;
import hr.fer.opp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value="/login")
    public String loginUser(@RequestBody User user) {
        return loginService.checkUser(user);
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestBody User user) {
        return loginService.registerUser(user);
    }

}
