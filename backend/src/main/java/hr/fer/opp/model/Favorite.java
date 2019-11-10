package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "favorites")
public class Favorite implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Citizen owner;
    @ManyToOne
    private Container container;

    public Favorite(Citizen owner, Container container) {
        this.owner = owner;
        this.container = container;
    }

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Favorite other = (Favorite) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", owner=" + owner +
                ", trashCan=" + container +
                '}';
    }

}
