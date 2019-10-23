package hr.fer.opp.dao;

import hr.fer.opp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Optional<Admin> findByEmail(String email);
}
