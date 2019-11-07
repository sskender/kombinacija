package hr.fer.opp.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CitizenController {

    @PostMapping(value="/trash/{id}/full")
    @Secured("CITIZEN")
    public String pingFull(@PathVariable("id") Long id){
        System.out.println("ULETIOOOOOOOOOOOO");
        return "ping successful";
    }

}
