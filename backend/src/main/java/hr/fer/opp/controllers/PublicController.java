package hr.fer.opp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping(value = "/trash/{id}/history")
    public String trashHistory(@PathVariable("id") Long id) {
        return "Fetching emptying history for container " + id.toString();
    }

    @GetMapping(value = "/map")
    public String map(
            @RequestParam("lat") Long latitude,
            @RequestParam("lon") Long longitude,
            @AuthenticationPrincipal User u) {
        return "Fetching list of containers and list of permitted actions when user is: " + u.getUsername();
    }

}
