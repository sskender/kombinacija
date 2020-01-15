package hr.fer.opp.controllers;

import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.dto.response.ContainerEventREST;
import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.NeighborhoodREST;
import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.model.Person;
import hr.fer.opp.model.Container;
import hr.fer.opp.services.AdminService;
import hr.fer.opp.services.PersonService;
import hr.fer.opp.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Person p = personService.fetchByEmail(userDetails.getUsername());
        return new ResponseEntity<>(
                new PersonREST(p, clearance(p.getId()).getBody()),
                HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PersonREST> registerUser(@RequestBody RegisterDTO registerDTO) {
        return new ResponseEntity<>(new PersonREST(publicService.registerCitizen(registerDTO), "citizen"), HttpStatus.CREATED);
    }

	@GetMapping(value = "/history/container/{id}")
	public ResponseEntity<List<ContainerEventREST>> containerHistory(@PathVariable("id") Long containerId) {
		Container c = adminService.getContainerById(containerId);
		return new ResponseEntity<>(
				ContainerEventREST.convertToREST(c.getPings(), c.getEmptyings(), 12), HttpStatus.OK);
	}

	@GetMapping(value = "/map")
	public ResponseEntity<List<ContainerREST>> map(@RequestParam(value = "lat") Double latitude,
			@RequestParam(value = "lon") Double longitude) {
		return new ResponseEntity<>(
				ContainerREST.convertToREST(publicService.getContainersInRadius(latitude, longitude)), HttpStatus.OK);
	}

	@GetMapping(value = "/map/{id}")
	public ResponseEntity<List<ContainerREST>> mapNeighborhood(@PathVariable("id") Long hoodId) {
		return new ResponseEntity<>(
				ContainerREST.convertToREST(adminService.getContainersByNeighborhoodId(hoodId)), HttpStatus.OK);
	}

    @GetMapping(value="/clearance")
    public ResponseEntity<String> clearance(@RequestParam(value = "uid", required = false) Long userId) {
        return new ResponseEntity<>(publicService.getClearance(userId), HttpStatus.OK);
    }

    @GetMapping(value="/hoods")
    public ResponseEntity<List<NeighborhoodREST>> getHoods(){
        return new ResponseEntity<>(NeighborhoodREST.convertToREST(adminService.getAllNeighborhoods()), HttpStatus.OK);
    }
}
