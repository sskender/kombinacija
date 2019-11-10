package hr.fer.opp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(value = "/route")
    public String route(@AuthenticationPrincipal User u) {
        return "Fetching list of containers to be emptied by " + u.getUsername();
    }

    @PostMapping(value = "/empty/{id}")
    public String empty(@PathVariable("id") Long contID,
                        @AuthenticationPrincipal User u) {
        return "User " + u.getUsername() + " emptying container " + contID;
    }

}
