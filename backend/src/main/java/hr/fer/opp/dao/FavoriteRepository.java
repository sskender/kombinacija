package hr.fer.opp.dao;

import hr.fer.opp.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Favorite> findById(Long id);

    /**
     * Select favorites where owner id matches given ownerId.
     *
     * @param ownerId
     * @return
     */
    List<Favorite> findByOwner_Id(Long ownerId);

    /**
     * Find favorites where owner id matches given ownerId
     * and container id matches given containerId.
     *
     * @param ownerId
     * @param containerId
     * @return
     */
    List<Favorite> findByOwner_IdAndContainer_Id(Long ownerId, Long containerId);

    /**
     * @param favorite
     * @return
     */
    default boolean insertFavorite(Favorite favorite) {
        return favorite.equals(save(favorite));
    }

    /**
     * @param favorites
     * @return
     */
    default int insertAllFavorites(Iterable<Favorite> favorites) {
        return saveAll(favorites).size();
    }

}
