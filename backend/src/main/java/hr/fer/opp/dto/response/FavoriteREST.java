package hr.fer.opp.dto.response;

import hr.fer.opp.model.Favorite;

import java.util.ArrayList;
import java.util.List;


public class FavoriteREST {
    private long id;
    private long ownerID;
    private long containerID;

    public FavoriteREST(Favorite f) {
        this.id = f.getId();
        this.ownerID = f.getOwner().getId();
        this.containerID = f.getContainer().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getContainerID() {
        return containerID;
    }

    public void setContainerID(long containerID) {
        this.containerID = containerID;
    }

    public static List<FavoriteREST> convertToREST(List<Favorite> favorites) {
        List<FavoriteREST> favoriteREST = new ArrayList<>();
        for (Favorite f : favorites) {
            favoriteREST.add(new FavoriteREST(f));
        }
        return favoriteREST;
    }
}
