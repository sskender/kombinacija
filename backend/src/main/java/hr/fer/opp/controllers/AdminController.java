package hr.fer.opp.controllers;

import hr.fer.opp.dto.request.AddContainerDTO;
import hr.fer.opp.dto.request.AddNeighborhoodDTO;
import hr.fer.opp.dto.request.RegisterEmployeeDTO;
import hr.fer.opp.dto.response.ContainerREST;
import hr.fer.opp.dto.response.NeighborhoodREST;
import hr.fer.opp.dto.response.PersonREST;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping(value = "/trash")
	public ResponseEntity<List<ContainerREST>> listAllContainers() {
		return new ResponseEntity<>(
				ContainerREST.convertToREST(adminService.getAllContainers()),
				HttpStatus.OK
		);
	}

	@PostMapping(value = "/trash")
	public ResponseEntity<ContainerREST> registerContainer(@RequestBody AddContainerDTO containerDTO) {
		return new ResponseEntity<>(
				new ContainerREST(adminService.registerNewContainer(containerDTO)),
				HttpStatus.CREATED
		);
	}

	@PutMapping(value = "/trash/{id}")
	public ResponseEntity<ContainerREST> updateContainer(
			@RequestBody AddContainerDTO containerDTO,
			@PathVariable("id") Long containerId
	) {
		return new ResponseEntity<>(
				new ContainerREST(adminService.updateContainer(containerDTO, containerId)),
				HttpStatus.OK
		);
	}

	@DeleteMapping(value = "/trash/{id}")
	public ResponseEntity<Boolean> deleteContainer(@PathVariable("id") Long containerId) {
		return new ResponseEntity<>(
				adminService.deleteContainerById(containerId),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/employee")
	public ResponseEntity<List<PersonREST>> listAllEmployees() {
		return new ResponseEntity<>(
				PersonREST.convertToREST(adminService.getAllEmployees()),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<PersonREST> getEmployee(@PathVariable("id") Long employeeId) {
		return new ResponseEntity<>(
				new PersonREST(adminService.getEmployeeById(employeeId), "employee"),
				HttpStatus.OK
		);
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<PersonREST> registerEmployee(@RequestBody RegisterEmployeeDTO registerEmployeeDTO) {
		return new ResponseEntity<>(
				new PersonREST(adminService.registerNewEmployee(registerEmployeeDTO), "employee"),
				HttpStatus.CREATED
		);
	}

	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<PersonREST> updateEmployee(
			@RequestBody RegisterEmployeeDTO registerEmployeeDTO,
			@PathVariable("id") Long employeeId
	) {
		return new ResponseEntity<>(
				new PersonREST(adminService.updateEmployeeProfile(registerEmployeeDTO, employeeId), "employee"),
				HttpStatus.OK
		);
	}

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Long employeeId) {
		return new ResponseEntity<>(
				adminService.removeEmployeeById(employeeId),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/neighborhood")
	public ResponseEntity<List<NeighborhoodREST>> listAllNeighborhoods() {
		return new ResponseEntity<>(
				NeighborhoodREST.convertToREST(adminService.getAllNeighborhoods()),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/neighborhood/{id}")
	public ResponseEntity<Neighborhood> getNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return new ResponseEntity<>(
				adminService.getNeighborhoodById(neighborhoodId),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/neighborhood/{id}/trash")
	public ResponseEntity<List<ContainerREST>> listAllContainersInNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return new ResponseEntity<>(
				ContainerREST.convertToREST(adminService.getContainersByNeighborhoodId(neighborhoodId)),
				HttpStatus.OK
		);
	}

	@GetMapping(value = "/neighborhood/{id}/employee")
	public ResponseEntity<List<PersonREST>> listAllEmployeessInNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return new ResponseEntity<>(
				PersonREST.convertToREST(adminService.getEmployeesByNeighborhoodId(neighborhoodId)),
				HttpStatus.OK
		);
	}

	@PostMapping(value = "/neighborhood")
	public ResponseEntity<NeighborhoodREST> registerNeighborhood(@RequestBody AddNeighborhoodDTO registerNeighborhoodDTO) {
		return new ResponseEntity<>(
				new NeighborhoodREST(adminService.registerNewNeighborhood(registerNeighborhoodDTO)),
				HttpStatus.CREATED
		);
	}

	@PutMapping(value = "/neighborhood/{id}")
	public ResponseEntity<NeighborhoodREST> updateNeighborhood(
			@RequestBody AddNeighborhoodDTO registerNeighborhoodDTO,
			@PathVariable("id") Long neighborhoodId
	) {
		return new ResponseEntity<>(
				new NeighborhoodREST(adminService.updateNeighborhood(registerNeighborhoodDTO, neighborhoodId)),
				HttpStatus.OK
		);
	}

	@DeleteMapping(value = "/neighborhood/{id}")
	public ResponseEntity<Boolean> deleteNeighborhood(@PathVariable("id") Long neighborhoodId) {
		return new ResponseEntity<>(
				adminService.deleteNeighborhoodById(neighborhoodId),
				HttpStatus.OK
		);
	}

}
