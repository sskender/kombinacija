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
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private double latitude;
    @OneToMany(mappedBy = "neighborhood")
    private List<Container> containers;
    @OneToMany(mappedBy = "neighborhood")
    private List<Employee> assignedEmployees;

    public Neighborhood(String name, List<Container> containers, List<Employee> assignedEmployees) {
        this.name = name;
        this.containers = containers;
        this.assignedEmployees = assignedEmployees;
    }

    public Neighborhood() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
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
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", containers=" + containers +
                ", assignedEmployees=" + assignedEmployees +
                '}';
    }
}

