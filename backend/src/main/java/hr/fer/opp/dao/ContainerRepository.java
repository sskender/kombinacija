package hr.fer.opp.dao;

import hr.fer.opp.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Container> findById(Long id);

    /**
     * @param container
     * @return
     */
    default boolean insertContainer(Container container) {
        return container.equals(save(container));
    }
}
