package hr.fer.opp.dao;

import hr.fer.opp.model.Emptying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmptyingRepository extends JpaRepository<Emptying, Long> {

    /**
     * Find emptyings of container, given container id.
     *
     * @param id
     * @return
     */
    List<Emptying> findByContainer_Id(Long id);

    /**
     * Find emptyings of container, given container id
     * and sorted by timestamp ascending.
     *
     * @param id
     * @return
     */
    List<Emptying> findByContainer_IdOrderByTimestamp(Long id);

    /**
     * Find emptyings of container, given container id
     * and sorted by timestamp descending.
     *
     * @param id
     * @return
     */
    List<Emptying> findByContainer_IdOrderByTimestampDesc(Long id);

}
