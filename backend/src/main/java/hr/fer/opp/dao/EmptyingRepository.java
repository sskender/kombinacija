package hr.fer.opp.dao;

import hr.fer.opp.model.Emptying;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmptyingRepository extends JpaRepository<Emptying, Long> {
}
