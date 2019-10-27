package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "citizens")
public class Citizen implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 100, nullable = false)
	private String email;
	@Column(length = 100, nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private int reputation;
	@OneToMany(mappedBy = "creator")
	private List<Ping> pings;
	@OneToMany(mappedBy = "owner")
	private List<Favorite> favorites;

	public Citizen(String email, String passwordHash, int reputation, List<Ping> pings, List<Favorite> favorites) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.reputation = reputation;
		this.pings = pings;
		this.favorites = favorites;
	}

	public Citizen() {};

	public Long getId() {
		return id;
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

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public List<Ping> getPings() {
		return pings;
	}

	public void setPings(List<Ping> pings) {
		this.pings = pings;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
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
		Citizen other = (Citizen) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Citizen{" +
				"id=" + id +
				", email='" + email + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", reputation=" + reputation +
				", pings=" + pings +
				", favorites=" + favorites +
				'}';
	}

}