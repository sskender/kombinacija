package hr.fer.opp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {

    @GetMapping(value = "/route")
    public String route(@AuthenticationPrincipal User u) {
        // TODO
        return "Fetching list of containers to be emptied by " + u.getUsername();
    }

    @PostMapping(value = "/empty/{id}")
    public String empty(@PathVariable("id") Long contID,
                        @AuthenticationPrincipal User u) {
        // TODO
        return "User " + u.getUsername() + " emptying container " + contID;
    }

}
