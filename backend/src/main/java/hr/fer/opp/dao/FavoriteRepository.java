package hr.fer.opp.dao;

import hr.fer.opp.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Favorite> findById(Long id);

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
