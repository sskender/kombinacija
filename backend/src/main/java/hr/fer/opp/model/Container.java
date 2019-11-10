package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "containers")
public class Container implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	@Column(nullable = false)
	private int pingsSinceEmptied;

	@ManyToOne
	private Neighborhood neighborhood;

	@OneToMany(mappedBy = "container")
	private List<Ping> pings;

	@OneToMany(mappedBy = "container")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "container")
	private List<Emptying> emptyings;

	public Container(double latitude, double longitude, int pingCount,
					 Neighborhood neighborhood, List<Ping> pings, List<Favorite> favorites,
					 List<Emptying> emptyings) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.pingsSinceEmptied = pingCount;
		this.neighborhood = neighborhood;
		this.pings = pings;
		this.favorites = favorites;
		this.emptyings = emptyings;
	}

	public Container() {
	}

	public Long getId() {
		return id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getPingsSinceEmptied() {
		return pingsSinceEmptied;
	}

	public void setPingsSinceEmptied(int pingsSinceEmptied) {
		this.pingsSinceEmptied = pingsSinceEmptied;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
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

	public List<Emptying> getEmptyings() {
		return emptyings;
	}

	public void setEmptyings(List<Emptying> emptyings) {
		this.emptyings = emptyings;
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
		Container other = (Container) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "TrashCan{" +
				"id=" + id +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", pingCounter=" + pingsSinceEmptied +
				", neighborhood=" + neighborhood +
				", pings=" + pings +
				", favorites=" + favorites +
				'}';
	}

}
