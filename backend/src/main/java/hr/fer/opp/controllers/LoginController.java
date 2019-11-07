package hr.fer.opp.controllers;

import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/register")
    public ResponseEntity<Citizen> registerUser(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(loginService.registerUser(registerDTO), HttpStatus.CREATED);
    }

}
