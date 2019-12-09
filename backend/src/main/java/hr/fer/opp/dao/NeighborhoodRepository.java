package hr.fer.opp.dao;

import hr.fer.opp.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

	/**
	 * @param id
	 * @return
	 */
	Optional<Neighborhood> findById(Long id);

	/**
	 * @param neighborhoodName
	 * @return
	 */
	Optional<Neighborhood> findByName(String neighborhoodName);

	/**
	 * @param neighborhood
	 * @return
	 */
	default boolean insertNeighborhood(Neighborhood neighborhood) {
		return neighborhood.equals(save(neighborhood));
	}

}
