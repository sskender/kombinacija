package hr.fer.opp.dao;

import hr.fer.opp.model.Ping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PingRepository extends JpaRepository<Ping, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Ping> findById(Long id);

    /**
     * @param ping
     * @return
     */
    default boolean insertPing(Ping ping) {
        return ping.equals(save(ping));
    }

}
