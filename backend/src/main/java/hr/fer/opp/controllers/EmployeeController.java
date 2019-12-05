package hr.fer.opp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {

    @GetMapping(value = "/route")
    public String route(@AuthenticationPrincipal UserDetails userDetails) {
        // TODO
        return "Fetching list of containers to be emptied by " + userDetails.getUsername();
    }

    @PostMapping(value = "/empty/{id}")
    public String empty(
            @PathVariable("id") Long contID,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO
        return "User " + userDetails.getUsername() + " emptying container " + contID;
    }

}
