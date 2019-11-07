package hr.fer.opp.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class CitizenController {

    @PostMapping(value="/ping/{id}/f")
    public String pingFull(@PathVariable("id") Long id){
        return "Pinging container "+id.toString()+" as full";
    }
    @PostMapping(value="/ping/{id}/u")
    public String pingUrgent(@PathVariable("id") Long id){
        return "Pinging container "+id.toString()+" as urgent";
    }
    @PostMapping(value="/ping/{id}/e")
    public String pingEmpty(@PathVariable("id") Long id){
        return "Pinging container "+id.toString()+" as empty";
    }

    @GetMapping(value="/favorite")
    public String favorites(){
        return "Listing all favorites";
    }
    @PostMapping(value="/favorite/{id}")
    public String addFavorite(@PathVariable("id") Long contID,
                              @AuthenticationPrincipal User u){
        return "Adding container "+contID+" as "+u.getUsername()+"'s favorite";
    }
    @DeleteMapping(value="/favorite/{id}")
    public String deleteFavorite(@PathVariable("id") Long contID,
                              @AuthenticationPrincipal User u){
        return "Removing container "+contID+" as "+u.getUsername()+"'s favorite";
    }
}
