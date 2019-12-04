package hr.fer.opp.services;

import hr.fer.opp.dto.RegisterDTO;
import hr.fer.opp.model.Citizen;

public interface PublicService {

    /**
     * Registers citizen in the citizens table
     * Admins can only be manually inserted
     *
     * @param registerDTO Registered citizen
     * @return
     */
    Citizen registerCitizen(RegisterDTO registerDTO);

}
