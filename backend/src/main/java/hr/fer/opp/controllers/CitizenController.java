package hr.fer.opp.controllers;

import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CitizenController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/auth")
    public ResponseEntity<PersonREST> testAuthorization(@AuthenticationPrincipal User u) {
        return new ResponseEntity<>(
                new PersonREST(personService.fetchByEmail(u.getUsername())),
                HttpStatus.OK);
    }

    @PostMapping(value = "/ping/{id}/f")
    public String pingFull(@PathVariable("id") Long id) {
        // TODO
        return "Pinging container " + id.toString() + " as full";
    }

    @PostMapping(value = "/ping/{id}/u")
    public String pingUrgent(@PathVariable("id") Long id) {
        // TODO
        return "Pinging container " + id.toString() + " as urgent";
    }

    @PostMapping(value = "/ping/{id}/e")
    public String pingEmpty(@PathVariable("id") Long id) {
        // TODO
        return "Pinging container " + id.toString() + " as empty";
    }

    @GetMapping(value = "/favorite")
    public String favorites() {
        // TODO
        return "Listing all favorites";
    }

    @PostMapping(value = "/favorite/{id}")
    public String addFavorite(@PathVariable("id") Long contID,
                              @AuthenticationPrincipal User u) {
        // TODO
        // TODO error crash if user = null
        return "Adding container " + contID + " as " + u.getUsername() + "'s favorite";
    }

    @DeleteMapping(value = "/favorite/{id}")
    public String deleteFavorite(@PathVariable("id") Long contID,
                                 @AuthenticationPrincipal User u) {
        // TODO
        return "Removing container " + contID + " as " + u.getUsername() + "'s favorite";
    }

}
