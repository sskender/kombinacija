package hr.fer.opp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {

	@Id @GeneratedValue
	private Long id;
	@Column(length=100, nullable=false)
	private String email;
	@Column(length=100, nullable=false)
	private String passwordHash;
	@Column(length=100, nullable=false)
	private String name;
	@Column(length=100, nullable=false)
	private String lastName;
	@Column(length=100, nullable=false)
	private String OIB;
	@ManyToOne
	private Neighborhood neighborhood;

	public Employee(String email, String passwordHash, String name, String lastName, String OIB) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		this.lastName = lastName;
		this.OIB = OIB;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + ", name=" + name
				+ ", lastName=" + lastName + ", OIB=" + OIB + ", neighborhood=" + neighborhood + "]";
	}
	
}
