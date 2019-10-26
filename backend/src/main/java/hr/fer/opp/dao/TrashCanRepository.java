package hr.fer.opp.dao;

import hr.fer.opp.model.TrashCan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrashCanRepository extends JpaRepository<TrashCan, Long> {

    /**
     * @param id
     * @return
     */
    Optional<TrashCan> findById(Long id);

    /**
     * @param trashCan
     * @return
     */
    default boolean insertTrashCan(TrashCan trashCan) {
        return trashCan.equals(save(trashCan));
    }
}
