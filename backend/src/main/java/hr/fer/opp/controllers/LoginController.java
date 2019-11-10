package hr.fer.opp.controllers;

import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value="/auth")
    public String testAuthorization(){
        return "Authorization successful!";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Citizen> registerUser(@RequestBody RegisterDTO registerDTO) {
        registerDTO.setPwd(new BCryptPasswordEncoder().encode(registerDTO.getPwd()));
        return new ResponseEntity<>(loginService.registerUser(registerDTO), HttpStatus.CREATED);
    }

}
