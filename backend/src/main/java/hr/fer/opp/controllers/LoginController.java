package hr.fer.opp.controllers;

import hr.fer.opp.dto.PersonREST;
import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.services.LoginService;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/auth")
    public ResponseEntity<PersonREST> testAuthorization(@AuthenticationPrincipal User u) {
        return new ResponseEntity<>(
                new PersonREST(personService.fetchByEmail(u.getUsername())),
                HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Citizen> registerUser(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(loginService.registerUser(registerDTO), HttpStatus.CREATED);
    }

}
