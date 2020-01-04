package hr.fer.opp.services;

import hr.fer.opp.dto.request.RegisterDTO;
import hr.fer.opp.model.Citizen;
import hr.fer.opp.model.Container;

import java.util.List;

public interface PublicService {

    /**
     * Registers citizen in the citizens table
     * Admins can only be manually inserted
     *
     * @param registerDTO Registered citizen
     * @return
     */
    Citizen registerCitizen(RegisterDTO registerDTO);

    List<Container> getContainersInRadius(Long latitude, Long longitude);

    String getClearance(Long userId);
}
