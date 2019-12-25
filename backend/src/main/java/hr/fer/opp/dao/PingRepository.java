package hr.fer.opp.dao;

import hr.fer.opp.model.Ping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PingRepository extends JpaRepository<Ping, Long> {

    /**
     * Find ping by ping ID.
     *
     * @param id
     * @return
     */
    Optional<Ping> findById(Long id);

    /**
     * Insert new ping object into database.
     *
     * @param ping
     * @return
     */
    default boolean insertPing(Ping ping) {
        return ping.equals(save(ping));
    }

    /**
     * Find pings where container_id matches given id.
     *
     * @param id
     * @return
     */
    List<Ping> findByContainer_Id(Long id);

    /**
     * Find pings where container_id matches given id
     * and ping timestamp is greater (newer) than given timestamp.
     *
     * @param id
     * @param timestamp
     * @return
     */
    List<Ping> findByContainer_IdAndTimestampGreaterThan(Long id, long timestamp);

}
