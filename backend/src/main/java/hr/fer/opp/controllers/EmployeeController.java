package hr.fer.opp.controllers;

import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.EmptyingREST;
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
    public ResponseEntity<List<ContainerREST>> getRoute(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/empty/{id}")
    public ResponseEntity<EmptyingREST> emptyContainer(
            @PathVariable("id") Long continerID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                new EmptyingREST(employeeService.emptyContainer(continerID, personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/report/{id}/legit")
    public ResponseEntity<Boolean> reportLegitimatePing(@PathVariable("id") Long containerId) {
        return new ResponseEntity<>(
                employeeService.reportLegitimatePing(containerId),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/report/{id}/fake")
    public ResponseEntity<Boolean> reportFakePing(@PathVariable("id") Long containerId) {
        return new ResponseEntity<>(
                employeeService.reportFakePing(containerId),
                HttpStatus.OK
        );
    }

}
