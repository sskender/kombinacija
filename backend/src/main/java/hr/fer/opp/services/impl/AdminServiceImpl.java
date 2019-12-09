package hr.fer.opp.services.impl;

import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.NeighborhoodRepository;
import hr.fer.opp.dto.request.AddContainerDTO;
import hr.fer.opp.dto.request.AddNeighborhoodDTO;
import hr.fer.opp.dto.request.RegisterEmployeeDTO;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.model.enums.RouteStatus;
import hr.fer.opp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private ContainerRepository containerRepository;

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Container getContainerById(Long containerId) {
		Optional<Container> o = containerRepository.findById(containerId);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException("Continer with given id does not exist.");
	}

	@Override
	public List<Container> getContainersByNeighborhoodId(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get().getContainers();
		throw new RequestDeniedException("Neighborhood with given id does not exist.");
	}

	@Override
	public List<Container> getAllContainers() {
		return containerRepository.findAll();
	}

	@Override
	@Transactional
	public Container registerNewContainer(AddContainerDTO containerDTO) {
		Container c = new Container();

		c.setNeighborhood(containerDTO.getNeighborhood());
		c.setLatitude(containerDTO.getLatitude());
		c.setLongitude(containerDTO.getLongitude());
		c.setPingsSinceEmptied(0);
		c.setRouteStatus(RouteStatus.PENDING);
		c.setPings(new ArrayList<>());
		c.setFavorites(new ArrayList<>());
		c.setEmptyings(new ArrayList<>());

		return containerRepository.save(c);
	}

	@Override
	public Container updateContainer(AddContainerDTO containerDTO, Long containerId) {
		Optional<Container> o = containerRepository.findById(containerId);
		if (o.isPresent()) {
			o.get().setLatitude(containerDTO.getLatitude());
			o.get().setLongitude(containerDTO.getLongitude());
			o.get().setNeighborhood(containerDTO.getNeighborhood());

			return containerRepository.save(o.get());
		}
		throw new RequestDeniedException("Continer with given id does not exist.");
	}

	@Override
	public boolean deleteContainerById(Long containerId) {
		Optional<Container> o = containerRepository.findById(containerId);
		if (o.isPresent()) {
			containerRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException("Continer with given id does not exist.");
	}

	@Override
	public Neighborhood getNeighborhoodById(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException("Neighborhood with given id does not exist.");
	}

	@Override
	public Neighborhood getNeighborhoodByName(String neighborhoodName) {
		Optional<Neighborhood> o = neighborhoodRepository.findByName(neighborhoodName);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException("Neighborhood with given name does not exist.");
	}

	@Override
	public List<Neighborhood> getAllNeighborhoods() {
		return neighborhoodRepository.findAll();
	}

	@Override
	@Transactional
	public Neighborhood registerNewNeighborhood(AddNeighborhoodDTO neighborhoodDTO) {
		if (neighborhoodRepository.findByName(neighborhoodDTO.getName()).isPresent()) {
			throw new RequestDeniedException("Neighborhood with given name already exists.");
		}
		Neighborhood n = new Neighborhood();

		n.setName(neighborhoodDTO.getName());
		n.setLatitude(neighborhoodDTO.getCenterLatitude());
		n.setLongitude(neighborhoodDTO.getCenterLongitude());
		n.setWorkerCapacity(0);
		n.setContainers(new ArrayList<>());
		n.setAssignedEmployees(new ArrayList<>());

		return neighborhoodRepository.save(n);
	}

	@Override
	public Neighborhood updateNeighborhood(AddNeighborhoodDTO neighborhoodDTO, Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent()) {
			o.get().setLatitude(neighborhoodDTO.getCenterLatitude());
			o.get().setLongitude(neighborhoodDTO.getCenterLongitude());
			o.get().setName(neighborhoodDTO.getName());

			return neighborhoodRepository.save(o.get());
		}
		throw new RequestDeniedException("Neighborhood with given id does not exist.");

	}

	@Override
	public boolean deleteNeighborhoodById(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent()) {
			neighborhoodRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException("Neighborhood with given id does not exist.");
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> o = employeeRepository.findById(employeeId);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException("Employee with given id does not exist.");
	}

	@Override
	public Employee getEmployeeByOIB(String OIB) {
		Optional<Employee> o = employeeRepository.findByOIB(OIB);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException("Employee with given OIB does not exist.");
	}

	@Override
	public List<Employee> getEmployeesByNeighborhoodId(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get().getAssignedEmployees();
		throw new RequestDeniedException("Neighborhood with given id does not exist.");
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee registerNewEmployee(RegisterEmployeeDTO employeeDTO) {
		if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
			throw new RequestDeniedException("Employee with given e-mail already exists.");
		}
		Employee e = new Employee();

		e.setName(employeeDTO.getName());
		e.setLastName(employeeDTO.getLastName());
		e.setEmail(employeeDTO.getEmail());
		e.setPwdHash(encoder.encode(employeeDTO.getPwd()));
		e.setOIB(employeeDTO.getOIB());
		e.setPings(new ArrayList<>());
		e.setFavorites(new ArrayList<>());
		e.setEmptyings(new ArrayList<>());

		return employeeRepository.save(e);
	}

	@Override
	public Employee updateEmployeeProfile(RegisterEmployeeDTO employeeDTO, Long employeeId) {
		Optional<Employee> o = employeeRepository.findById(employeeId);
		if (o.isPresent()) {
			o.get().setName(employeeDTO.getName());
			o.get().setLastName(employeeDTO.getLastName());
			o.get().setEmail(employeeDTO.getEmail());
			o.get().setPwdHash(encoder.encode(employeeDTO.getPwd()));
			o.get().setOIB(employeeDTO.getOIB());

			return employeeRepository.save(o.get());
		}
		throw new RequestDeniedException("Employee with given id does not exist.");

	}

	@Override
	public boolean removeEmployeeById(Long employeeId) {
		Optional<Employee> o = employeeRepository.findById(employeeId);
		if (o.isPresent()) {
			employeeRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException("Employee with given id does not exist.");
	}

}
