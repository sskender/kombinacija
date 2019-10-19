package hr.fer.opp.dao;

import hr.fer.opp.model.Ping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PingRepository extends JpaRepository<Ping, Long> {
}
