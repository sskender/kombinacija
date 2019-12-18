package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.EmployeeRepository;
import hr.fer.opp.dao.NeighborhoodRepository;
import hr.fer.opp.dto.request.AddContainerDTO;
import hr.fer.opp.dto.request.AddNeighborhoodDTO;
import hr.fer.opp.dto.request.RegisterEmployeeDTO;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.model.enums.RouteStatus;
import hr.fer.opp.services.AdminService;
import hr.fer.opp.exceptions.ExceptionMessages;
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
	private CitizenRepository citizenRepository;

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
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
	}

	@Override
	public List<Container> getContainersByNeighborhoodId(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get().getContainers();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	public List<Container> getAllContainers() {
		return containerRepository.findAll();
	}

	@Override
	@Transactional
	public Container registerNewContainer(AddContainerDTO containerDTO) {
		Container c = new Container();
		Optional<Neighborhood> n = neighborhoodRepository.findById(containerDTO.getNeighborhoodId());
		if (n.isPresent()) {
			c.setNeighborhood(n.get());
		} else {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_REGISTER
					+ ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
		}

		c.setLatitude(containerDTO.getLatitude());
		c.setLongitude(containerDTO.getLongitude());
		c.setPingsSinceEmptied(Container.DEFAULT_PINGS_SINCE_EMPTIED);
		c.setRouteStatus(RouteStatus.PENDING);
		c.setPings(new ArrayList<>());
		c.setFavorites(new ArrayList<>());
		c.setEmptyings(new ArrayList<>());

		n.get().getContainers().add(c);

		return containerRepository.save(c);
	}

	@Override
	@Transactional
	public Container updateContainer(AddContainerDTO containerDTO, Long containerId) {
		Optional<Container> c = containerRepository.findById(containerId);
		Optional<Neighborhood> n = neighborhoodRepository.findById(containerDTO.getNeighborhoodId());

		if (c.isPresent() && n.isPresent()) {
			c.get().getNeighborhood().getContainers().remove(c.get());

			c.get().setLatitude(containerDTO.getLatitude());
			c.get().setLongitude(containerDTO.getLongitude());
			c.get().setNeighborhood(n.get());

			n.get().getContainers().add(c.get());

			return containerRepository.save(c.get());
		}
		if (!c.isPresent())
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
		else
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_UPDATE
					+ ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	@Transactional
	public boolean deleteContainerById(Long containerId) {
		Optional<Container> o = containerRepository.findById(containerId);
		if (o.isPresent()) {
			containerRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
	}

	@Override
	public Neighborhood getNeighborhoodById(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	public Neighborhood getNeighborhoodByName(String neighborhoodName) {
		Optional<Neighborhood> o = neighborhoodRepository.findByName(neighborhoodName);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NAME_NOT_EXIST);
	}

	@Override
	public List<Neighborhood> getAllNeighborhoods() {
		return neighborhoodRepository.findAll();
	}

	@Override
	@Transactional
	public Neighborhood registerNewNeighborhood(AddNeighborhoodDTO neighborhoodDTO) {
		if (neighborhoodRepository.findByName(neighborhoodDTO.getName()).isPresent()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NAME_EXISTS);
		}
		Neighborhood n = new Neighborhood();

		n.setName(neighborhoodDTO.getName());
		n.setLatitude(neighborhoodDTO.getCenterLatitude());
		n.setLongitude(neighborhoodDTO.getCenterLongitude());
		n.setWorkerCapacity(Neighborhood.DEFAULT_WORKER_CAPACITY);
		n.setContainers(new ArrayList<>());
		n.setAssignedEmployees(new ArrayList<>());

		return neighborhoodRepository.save(n);
	}

	@Override
	@Transactional
	public Neighborhood updateNeighborhood(AddNeighborhoodDTO neighborhoodDTO, Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent()) {
			o.get().setLatitude(neighborhoodDTO.getCenterLatitude());
			o.get().setLongitude(neighborhoodDTO.getCenterLongitude());
			o.get().setName(neighborhoodDTO.getName());

			return neighborhoodRepository.save(o.get());
		}
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);

	}

	private Citizen convertEmployeeToCitizenOnDelete(Employee employee) {
		Citizen citizen = new Citizen();

		// citizen.setId(employee.getId());
		citizen.setName(employee.getName());
		citizen.setLastName(employee.getLastName());
		citizen.setEmail(employee.getEmail());
		citizen.setPwdHash(employee.getPwdHash());
		citizen.setPings(new ArrayList<>());
		citizen.setFavorites(new ArrayList<>());
		citizen.setReputation(Citizen.DEFAULT_CITIZEN_REPUTATION);

		return citizen;
	}

	@Override
	@Transactional
	public boolean deleteNeighborhoodById(Long neighborhoodId) {
		// delete assigned containers
		for (Container container : getContainersByNeighborhoodId(neighborhoodId)) {
			deleteContainer(container);
		}

		// convert assigned employees to citizens
		for (Employee employee : getEmployeesByNeighborhoodId(neighborhoodId)) {
			removeEmployee(employee);
		}

		// delete neighborhood
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent()) {
			neighborhoodRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> o = employeeRepository.findById(employeeId);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_NOT_EXIST);
	}

	@Override
	public Employee getEmployeeByOIB(String OIB) {
		Optional<Employee> o = employeeRepository.findByOIB(OIB);
		if (o.isPresent())
			return o.get();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_OIB_NOT_EXIST);
	}

	@Override
	public List<Employee> getEmployeesByNeighborhoodId(Long neighborhoodId) {
		Optional<Neighborhood> o = neighborhoodRepository.findById(neighborhoodId);
		if (o.isPresent())
			return o.get().getAssignedEmployees();
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee registerNewEmployee(RegisterEmployeeDTO employeeDTO) {
		Optional<Neighborhood> n = neighborhoodRepository.findById(employeeDTO.getNeighborhoodId());
		Employee e = new Employee();
		if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_EXISTS);
		}

		if (n.isPresent()) {
			e.setNeighborhood(n.get());
		} else {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_CAN_NOT_REGISTER
					+ ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
		}

		e.setName(employeeDTO.getName());
		e.setLastName(employeeDTO.getLastName());
		e.setEmail(employeeDTO.getEmail());
		e.setPwdHash(encoder.encode(employeeDTO.getPwd()));
		e.setOIB(employeeDTO.getOib());
		e.setPings(new ArrayList<>());
		e.setFavorites(new ArrayList<>());
		e.setEmptyings(new ArrayList<>());

		n.get().getAssignedEmployees().add(e);

		return employeeRepository.save(e);
	}

	@Override
	@Transactional
	public Employee updateEmployeeProfile(RegisterEmployeeDTO employeeDTO, Long employeeId) {
		Optional<Employee> e = employeeRepository.findById(employeeId);
		Optional<Neighborhood> n = neighborhoodRepository.findById(employeeDTO.getNeighborhoodId());

		if (e.isPresent() && n.isPresent()) {
			e.get().getNeighborhood().getAssignedEmployees().remove(e.get());
			e.get().setName(employeeDTO.getName());
			e.get().setLastName(employeeDTO.getLastName());
			e.get().setEmail(employeeDTO.getEmail());
			e.get().setPwdHash(encoder.encode(employeeDTO.getPwd()));
			e.get().setOIB(employeeDTO.getOib());
			e.get().setNeighborhood(n.get());

			n.get().getAssignedEmployees().add(e.get());

			return employeeRepository.save(e.get());
		}
		if (!e.isPresent())
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_NOT_EXIST);
		else
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_CAN_NOT_UPDATE
					+ ExceptionMessages.EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST);
	}

	@Override
	@Transactional
	public boolean removeEmployeeById(Long employeeId) {
		Optional<Employee> o = employeeRepository.findById(employeeId);

		if (o.isPresent()) {
			// employee is now citizen
			Citizen citizen = convertEmployeeToCitizenOnDelete(o.get());
			citizenRepository.save(citizen);

			// delete employee
			employeeRepository.delete(o.get());
			return true;
		}
		throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_EMPLOYEE_NOT_EXIST);
	}

}
