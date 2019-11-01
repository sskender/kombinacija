package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "emptyings")
public class Emptying implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private long timestamp;
    @ManyToOne
    private Employee worker;
    @ManyToOne
    private Container container;


    public Emptying(long timestamp, Employee worker, Container container) {
        this.timestamp = timestamp;
        this.worker = worker;
        this.container = container;
    }

    public Emptying() {};

    public Long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Person getWorker() {
        return worker;
    }
    public void setWorker(Employee worker) {
        this.worker = worker;
    }

    public Container getContainer() {
        return container;
    }
    public void setContainer(Container container) { this.container = container; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emptying)) return false;
        Emptying emptying = (Emptying) o;
        return id.equals(emptying.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Emptying{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", worker=" + worker +
                ", container=" + container +
                '}';
    }
}
