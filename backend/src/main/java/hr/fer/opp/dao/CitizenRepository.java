package hr.fer.opp.dao;

import hr.fer.opp.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Citizen> findById(Long id);

    /**
     * @param email
     * @return
     */
    Optional<Citizen> findByEmail(String email);

    /**
     * @param citizen
     */
    default boolean insertUser(Citizen citizen) {
        return citizen.equals(save(citizen));
    }

}
