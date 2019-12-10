package hr.fer.opp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dto.request.AddContainerDTO;
import hr.fer.opp.dto.request.AddNeighborhoodDTO;
import hr.fer.opp.dto.request.RegisterEmployeeDTO;
import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.NeighborhoodREST;
import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.services.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CitizenRepository citizenRepository;

	@GetMapping(value = "/trash")
	public List<ContainerREST> listAllContainers() {
		return ContainerREST.convertToREST(adminService.getAllContainers());
	}

	@PostMapping(value = "/trash")
	public ResponseEntity<ContainerREST> registerContainer(@RequestBody AddContainerDTO containerDTO) {
		return new ResponseEntity<>(new ContainerREST(adminService.registerNewContainer(containerDTO)),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/trash/{id}")
	public ResponseEntity<ContainerREST> updateContainer(@RequestBody AddContainerDTO containerDTO,
			@PathVariable("id") Long containerId) {
		return new ResponseEntity<>(new ContainerREST(adminService.updateContainer(containerDTO, containerId)),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/trash/{id}")
	public boolean deleteContainer(@PathVariable("id") Long containerId) {
		return adminService.deleteContainerById(containerId);
	}

	@GetMapping(value = "/employee")
	public List<PersonREST> listAllEmployees() {
		return PersonREST.convertToREST(adminService.getAllEmployees());
	}

	@GetMapping(value = "/employee/{id}")
	public PersonREST getEmployee(@PathVariable("id") Long employeeId) {
		return new PersonREST(adminService.getEmployeeById(employeeId), "employee");
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<PersonREST> registerEmployee(@RequestBody RegisterEmployeeDTO registerEmployeeDTO) {
		return new ResponseEntity<>(new PersonREST(adminService.registerNewEmployee(registerEmployeeDTO), "employee"),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<PersonREST> updateEmployee(@RequestBody RegisterEmployeeDTO registerEmployeeDTO,
			@PathVariable("id") Long employeeId) {
		return new ResponseEntity<>(
				new PersonREST(adminService.updateEmployeeProfile(registerEmployeeDTO, employeeId), "employee"),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/employee/{id}")
	public boolean deleteEmployee(@PathVariable("id") Long employeeId) {
		return adminService.removeEmployeeById(employeeId);
	}

	@GetMapping(value = "/neighborhood")
	public List<NeighborhoodREST> listAllNeighborhoods() {
		return NeighborhoodREST.convertToREST(adminService.getAllNeighborhoods());
	}

	@GetMapping(value = "/neighborhood/{id}")
	public Neighborhood getNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return adminService.getNeighborhoodById(neighborhoodId);
	}

	@GetMapping(value = "/neighborhood/{id}/trash")
	public List<ContainerREST> listAllContainersInNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return ContainerREST.convertToREST(adminService.getContainersByNeighborhoodId(neighborhoodId));
	}

	@GetMapping(value = "/neighborhood/{id}/employee")
	public List<PersonREST> listAllEmployeessInNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return PersonREST.convertToREST(adminService.getEmployeesByNeighborhoodId(neighborhoodId));
	}

	@PostMapping(value = "/neighborhood")
	public ResponseEntity<NeighborhoodREST> registerNeighborhood(
			@RequestBody AddNeighborhoodDTO registerNeighborhoodDTO) {
		return new ResponseEntity<>(new NeighborhoodREST(adminService.registerNewNeighborhood(registerNeighborhoodDTO)),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/neighborhood/{id}")
	public ResponseEntity<NeighborhoodREST> updateNeighborhood(@RequestBody AddNeighborhoodDTO registerNeighborhoodDTO,
			@PathVariable("id") Long neighborhoodId) {
		return new ResponseEntity<>(
				new NeighborhoodREST(adminService.updateNeighborhood(registerNeighborhoodDTO, neighborhoodId)),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/neighborhood/{id}")
	public boolean deleteNeighborhood(@PathVariable("id") Long neighborhoodId) {
		for (Container c : adminService.getContainersByNeighborhoodId(neighborhoodId)) {
			adminService.deleteContainerById(c.getId());
		}

		for (Employee e : adminService.getEmployeesByNeighborhoodId(neighborhoodId)) {
			Citizen c = new Citizen();

			c.setEmail(e.getEmail());
			c.setFavorites(new ArrayList<>());
			c.setId(e.getId());
			c.setLastName(e.getLastName());
			c.setName(e.getName());
			c.setPings(new ArrayList<>());
			c.setPwdHash(e.getPwdHash());
			c.setReputation(0);

			citizenRepository.save(c);
			adminService.removeEmployeeById(e.getId());
		}
		return adminService.deleteNeighborhoodById(neighborhoodId);
	}

}
