package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "neighborhoods")
public class Neighborhood implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @OneToMany(mappedBy = "neighborhood")
    private List<TrashCan> trashCans;
    @OneToMany(mappedBy = "neighborhood")
    private List<Employee> assignedEmployees;

    public Neighborhood(String name, List<TrashCan> trashCans, List<Employee> assignedEmployees) {
        this.name = name;
        this.trashCans = trashCans;
        this.assignedEmployees = assignedEmployees;
    }

    public Neighborhood() {};

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrashCan> getTrashCans() {
        return trashCans;
    }

    public void setTrashCans(List<TrashCan> trashCans) {
        this.trashCans = trashCans;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
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
        Neighborhood other = (Neighborhood) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Neighborhood{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trashCans=" + trashCans +
                ", assignedEmployees=" + assignedEmployees +
                '}';
    }

}

