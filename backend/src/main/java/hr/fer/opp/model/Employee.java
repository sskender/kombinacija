package hr.fer.opp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends Person implements Serializable {

	@Column(nullable = false, unique = true)
	@NotNull
	@Size(min = 11, max = 11)
	private String OIB;

	@ManyToOne
	private Neighborhood neighborhood;

	@OneToMany(mappedBy = "worker")
	private List<Emptying> emptyings;

	public Employee() {
		super();
	}

	public String getOIB() {
		return OIB;
	}

	public void setOIB(String OIB) {
		this.OIB = OIB;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

	public List<Emptying> getEmptyings() {
		return emptyings;
	}

	public void setEmptyings(List<Emptying> emptyings) {
		this.emptyings = emptyings;
	}

	@Override
	public String toString() {
		String s = super.toString().replace("Person", "Employee");
		return s.replace("}", "\n\t, OIB=" + OIB + "\n\t, neighborhood=" + neighborhood + "}");
	}

}
