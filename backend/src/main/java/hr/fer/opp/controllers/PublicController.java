package hr.fer.opp.controllers;

import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.NeighborhoodREST;
import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.services.AdminService;
import hr.fer.opp.services.PersonService;
import hr.fer.opp.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PublicController {

    @Autowired
    private PublicService publicService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/auth")
    public ResponseEntity<PersonREST> testAuthorization(@AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity<>(
                new PersonREST(personService.fetchByEmail(userDetails.getUsername()), extractHighestAuthority(userDetails)),
                HttpStatus.OK);
    }

    private String extractHighestAuthority(UserDetails userDetails) {
        String role = "";
        for(GrantedAuthority a : userDetails.getAuthorities()) {
            if(a.getAuthority().equals("ADMIN")){
                role="admin";
            } else if(a.getAuthority().equals("EMPLOYEE") && !role.equals("admin")){
                role="employee";
            } else if (a.getAuthority().equals("CITIZEN") && !role.equals("admin") && !role.equals("employee")){
                role="citizen";
            }
        }
        return role;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PersonREST> registerUser(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(new PersonREST(publicService.registerCitizen(registerDTO), "citizen"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/history/container/{id}")
    public String containerHistory(@PathVariable("id") Long id) {
        // TODO
        return "Fetching emptying history for container " + id.toString();
    }

    @GetMapping(value = "/map")
    public ResponseEntity<List<ContainerREST>> map(
            @RequestParam(value="lat") Double latitude,
            @RequestParam(value = "lon") Double longitude
    ) {
        return new ResponseEntity<List<ContainerREST>>(ContainerREST.convertToREST(publicService.getContainersInRadius(latitude, longitude)), HttpStatus.OK);
    }

    @GetMapping(value = "/map/{id}")
    public ResponseEntity<List<ContainerREST>> mapNeighborhood(
            @PathVariable("id") Long hoodId
    ) {
        return new ResponseEntity<List<ContainerREST>>(ContainerREST.convertToREST(adminService.getContainersByNeighborhoodId(hoodId)), HttpStatus.OK);
    }

    @GetMapping(value="/clearance")
    public String clearance(@RequestParam(value = "uid", required = false) Long userId) {
        return publicService.getClearance(userId);
    }

    @GetMapping(value="/hoods")
    public ResponseEntity<List<NeighborhoodREST>> getHoods(){
        return new ResponseEntity<>(NeighborhoodREST.convertToREST(adminService.getAllNeighborhoods()), HttpStatus.OK);
    }
}
