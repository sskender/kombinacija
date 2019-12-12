package hr.fer.opp.controllers;

import hr.fer.opp.dto.response.FavoriteREST;
import hr.fer.opp.dto.response.PingREST;
import hr.fer.opp.services.CitizenService;
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
public class CitizenController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CitizenService citizenService;

    @PostMapping(value = "/ping/{id}/f")
    public ResponseEntity<PingREST> pingFull(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                new PingREST(citizenService.pingContainerFull(id, personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value = "/ping/{id}/u")
    public ResponseEntity<PingREST> pingUrgent(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                new PingREST(citizenService.pingContainerUrgent(id, personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value = "/ping/{id}/e")
    public ResponseEntity<PingREST> pingEmpty(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                new PingREST(citizenService.pingContainerEmpty(id, personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/favorite")
    public ResponseEntity<List<FavoriteREST>> favorites(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                FavoriteREST.convertToREST(citizenService.getFavoriteContainers(personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/favorite/{id}")
    public ResponseEntity<FavoriteREST> addFavorite(
            @PathVariable("id") Long contID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                new FavoriteREST(citizenService.addToFavorites(contID, personService.fetchByEmail(userDetails.getUsername()))),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(value = "/favorite/{id}")
    public ResponseEntity<Boolean> deleteFavorite(
            @PathVariable("id") Long contID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(
                citizenService.removeFromFavorites(contID, personService.fetchByEmail(userDetails.getUsername())),
                HttpStatus.OK
        );
    }

}
