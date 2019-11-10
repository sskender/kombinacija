package hr.fer.opp.dao;

import hr.fer.opp.model.Emptying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmptyingRepository extends JpaRepository<Emptying, Long> {
}
