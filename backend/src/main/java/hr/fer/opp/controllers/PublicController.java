package hr.fer.opp.controllers;

import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.model.Citizen;
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

    @PostMapping(value = "/register")
    public ResponseEntity<Citizen> registerUser(@RequestBody RegisterDTO registerDTO) {
        // TODO return dto, not citizen
        return new ResponseEntity<>(publicService.registerCitizen(registerDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/history/container/{id}")
    public String trashHistory(@PathVariable("id") Long id) {
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
