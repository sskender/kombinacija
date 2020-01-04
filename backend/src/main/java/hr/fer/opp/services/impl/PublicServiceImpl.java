package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.NeighborhoodRepository;
import hr.fer.opp.dao.PersonRepository;
import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.exceptions.ExceptionMessages;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Container;
import hr.fer.opp.model.Neighborhood;
import hr.fer.opp.model.Person;
import hr.fer.opp.services.PersonService;
import hr.fer.opp.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicServiceImpl implements PublicService {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	@Override
	@Transactional
	public Citizen registerCitizen(RegisterDTO registerDTO) {
		if (personRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_USER_EXISTS);
		}
		Citizen c = translate(registerDTO);
		return citizenRepository.save(c);
	}

	private Citizen translate(RegisterDTO registerDTO) {
		Citizen c = new Citizen();

		c.setEmail(registerDTO.getEmail());
		c.setPwdHash(encoder.encode(registerDTO.getPwd()));

		c.setName(registerDTO.getName());
		c.setLastName(registerDTO.getLastName());
		c.setReputation(0);

		c.setPings(new ArrayList<>());
		c.setFavorites(new ArrayList<>());

		return c;
	}

	@Override
	public List<Container> getContainersInRadius(Double latitude, Double longitude) {
		if(latitude==null || longitude==null){

		}
		List<Neighborhood> closestNeighborhoods = new ArrayList<>();
		List<Neighborhood> allHoods = neighborhoodRepository.findAll();
		allHoods.sort((n1, n2) -> {
			if (getDistance(n1.getLatitude(), n1.getLongitude(), latitude, longitude) > getDistance(n2.getLatitude(), n2.getLongitude(), latitude, longitude)) {
				return 1;
			} else if (getDistance(n1.getLatitude(), n1.getLongitude(), latitude, longitude) < getDistance(n2.getLatitude(), n2.getLongitude(), latitude, longitude)) {
				return -1;
			} else {
				return 0;
			}
		});
		List<Container> closeContainers = new ArrayList<>();
		for(int i=0, s=allHoods.size(); i<3 && i<s; i++){
			closeContainers.addAll(allHoods.get(i).getContainers());
		}
		closeContainers.sort((c1, c2) -> {
			if (getDistance(c1.getLatitude(), c1.getLongitude(), latitude, longitude) > getDistance(c2.getLatitude(), c2.getLongitude(), latitude, longitude)) {
				return 1;
			} else if (getDistance(c1.getLatitude(), c1.getLongitude(), latitude, longitude) < getDistance(c2.getLatitude(), c2.getLongitude(), latitude, longitude)) {
				return -1;
			} else {
				return 0;
			}
		});
		List<Container> result = new ArrayList<>();
		for(int i=0, s=closeContainers.size(); i<50 && i<s; i++){
			result.add(closeContainers.get(i));
		}
		return closeContainers;
	}

	private static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6371000; //meters
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;

		return dist;
	}

	@Override
	public String getClearance(Long userId) {
		if(userId==null){
			return "unregistered";
		} else {
			Person p = personRepository.getOne(userId);
			if(personService.isAdmin(userId)){
				return "admin";
			} else if(personService.isEmployee(userId)){
				return "employee";
			} else if(personService.isCitizen(userId)) {
				return "citizen";
			} else {
				return "unregistered";
			}
		}
	}
}
