package hr.fer.opp.services.impl;

import hr.fer.opp.dao.CitizenRepository;
import hr.fer.opp.dao.ContainerRepository;
import hr.fer.opp.dao.FavoriteRepository;
import hr.fer.opp.dao.PingRepository;
import hr.fer.opp.exceptions.ExceptionMessages;
import hr.fer.opp.exceptions.RequestDeniedException;
import hr.fer.opp.model.*;
import hr.fer.opp.model.enums.PingLevel;
import hr.fer.opp.services.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private ContainerRepository containerRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private PingRepository pingRepository;

	@Override
	@Transactional
	public int increaseReputation(Person creator) {
		Optional<Citizen> citizenOptional = citizenRepository.findByEmail(creator.getEmail());

		if (citizenOptional.isPresent()) {
			Citizen citizen = citizenOptional.get();
			citizen.setReputation(citizen.getReputation() + Citizen.CITIZEN_REPUTATION_INCREASE);

			return citizenRepository.save(citizen).getReputation();
		} else {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CITIZEN_NOT_EXIST);
		}
	}

	@Override
	@Transactional
	public int decreaseReputation(Person creator) {
		Optional<Citizen> citizenOptional = citizenRepository.findByEmail(creator.getEmail());

		if (citizenOptional.isPresent()) {
			Citizen citizen = citizenOptional.get();
			citizen.setReputation(citizen.getReputation() - Citizen.CITIZEN_REPUTATION_DECREASE);

			if (citizen.getReputation() < 0) {
				citizen.setReputation(Citizen.DEFAULT_CITIZEN_REPUTATION);
			}

			return citizenRepository.save(citizen).getReputation();
		} else {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CITIZEN_NOT_EXIST);
		}
	}

	@Override
	@Transactional
	public Favorite addToFavorites(Long containerId, Person owner) {
		// check container exists
		Optional<Container> containerOptional = containerRepository.findById(containerId);

		if (!containerOptional.isPresent()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
		}

		// check if container already marked as favorite
		List<Favorite> favoriteList = favoriteRepository.findByOwner_IdAndContainer_Id(owner.getId(), containerId);

		if (favoriteList.isEmpty()) {

			// create new favorite
			Favorite favorite = new Favorite();
			favorite.setContainer(containerOptional.get());
			favorite.setOwner(owner);

			containerOptional.get().getFavorites().add(favorite);
			owner.getFavorites().add(favorite);

			return favoriteRepository.save(favorite);

		} else {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_REGISTER_FAVORITE);
		}
	}

	@Override
	@Transactional
	public boolean removeFromFavorites(Long containerId, Person owner) {
		List<Favorite> favoriteList = favoriteRepository.findByOwner_IdAndContainer_Id(owner.getId(), containerId);

		if (favoriteList.isEmpty()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_FAVORITE_NOT_EXIST);
		} else {

			for (Favorite favorite : favoriteList) {
				favoriteRepository.delete(favorite);
			}

			return true;
		}

	}

	@Override
	public List<Favorite> getFavoriteContainers(Person owner) {
		return favoriteRepository.findByOwner_Id(owner.getId());
	}

	@Override
	@Transactional
	public Ping pingContainer(Long containerId, Person creator, PingLevel pingLevel) {
		// check container exists
		Optional<Container> containerOptional = containerRepository.findById(containerId);

		if (!containerOptional.isPresent()) {
			throw new RequestDeniedException(ExceptionMessages.EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST);
		}

		Container container = containerOptional.get();

		// create ping
		Ping ping = new Ping();
		ping.setCreator(creator);
		ping.setContainer(container);
		ping.setLevel(pingLevel);
		ping.setTimestamp(System.currentTimeMillis());
		ping.setPhotoPath(Ping.DEFAULT_PHOTO_PATH);

		// update person
		creator.getPings().add(ping);

		// update container
		container.getPings().add(ping);
		container.setPingsSinceEmptied(container.getPingsSinceEmptied() + 1);

		return pingRepository.save(ping);
	}

}
