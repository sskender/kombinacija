package hr.fer.opp.controllers;

import hr.fer.opp.model.Container;
import hr.fer.opp.services.EmployeeService;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/route")
    public ResponseEntity<List<Container>> getRoute(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                employeeService.getWorkRoute(personService.fetchByEmail(userDetails.getUsername())),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/empty/{id}")
    public ResponseEntity<Boolean> emptyContainer(
            @PathVariable("id") Long continerID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                employeeService.emptyContainer(continerID, personService.fetchByEmail(userDetails.getUsername())),
                HttpStatus.CREATED
        );
    }

}
