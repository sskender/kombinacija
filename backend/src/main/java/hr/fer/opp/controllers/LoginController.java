package hr.fer.opp.controllers;

import hr.fer.opp.model.UserLoginProfile;
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
    public String loginUser(@RequestBody UserLoginProfile userLoginProfile) {
        // user hash password
        // return loginService.checkUser(citizen);
        return null;
    }

    @PostMapping(value="/register")
    public String registerUser(@RequestBody UserLoginProfile userLoginProfile) {
        //return loginService.registerUser(citizen);
        return null;
    }

}
