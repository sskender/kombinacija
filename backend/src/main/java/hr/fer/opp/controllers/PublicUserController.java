package hr.fer.opp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicUserController {

    @GetMapping(value="/trash/{id}/history")
    public String trashHistory() {
        return "template";
    }
}
