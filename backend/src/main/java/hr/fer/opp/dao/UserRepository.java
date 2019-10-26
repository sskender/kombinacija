package hr.fer.opp.dao;

import hr.fer.opp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     * @param user
     */
    default boolean insertUser(User user) {
        return user.equals(save(user));
    }

}
