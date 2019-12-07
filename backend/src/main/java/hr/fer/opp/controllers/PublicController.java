package hr.fer.opp.controllers;

import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.services.PersonService;
import hr.fer.opp.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PublicController {

    @Autowired
    private PublicService publicService;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/auth")
    public ResponseEntity<PersonREST> testAuthorization(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                new PersonREST(personService.fetchByEmail(userDetails.getUsername())),
                HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PersonREST> registerUser(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(new PersonREST(publicService.registerCitizen(registerDTO)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/history/container/{id}")
    public String containerHistory(@PathVariable("id") Long id) {
        // TODO
        return "Fetching emptying history for container " + id.toString();
    }

    @GetMapping(value = "/map")
    public String map(
            @RequestParam("lat") Long latitude,
            @RequestParam("lon") Long longitude,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO
        return "Fetching list of containers and list of permitted actions when user is: " + userDetails.getUsername();
    }

}
