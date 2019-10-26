package hr.fer.opp.dao;

import hr.fer.opp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Admin> findById(Long id);

    /**
     * @param email
     * @return
     */
    Optional<Admin> findByEmail(String email);

    /**
     * @param admin
     * @return
     */
    default boolean insertAdmin(Admin admin) {
        return admin.equals(save(admin));
    }

}
