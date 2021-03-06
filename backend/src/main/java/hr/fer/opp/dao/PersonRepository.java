package hr.fer.opp.dao;

import hr.fer.opp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * @param email
     * @return
     */
    Optional<Person> findByEmail(String email);

}
