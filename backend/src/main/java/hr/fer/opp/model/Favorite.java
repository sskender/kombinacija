package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "favorites")
public class Favorite implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person owner;

    @ManyToOne
    private Container container;

    public Favorite() {
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorite)) return false;
        Favorite favorite = (Favorite) o;
        return getId().equals(favorite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", owner=" + owner +
                ", container=" + container +
                '}';
    }

}
