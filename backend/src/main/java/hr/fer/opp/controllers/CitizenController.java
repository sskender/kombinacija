package hr.fer.opp.controllers;

import hr.fer.opp.services.CitizenService;
import hr.fer.opp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CitizenController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CitizenService citizenService;

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
    public String addFavorite(
            @PathVariable("id") Long contID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO
        // TODO error crash if user = null
        return "Adding container " + contID + " as " + userDetails.getUsername() + "'s favorite";
    }

    @DeleteMapping(value = "/favorite/{id}")
    public String deleteFavorite(
            @PathVariable("id") Long contID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO
        return "Removing container " + contID + " as " + userDetails.getUsername() + "'s favorite";
    }

}
