package hr.fer.opp.model;

import hr.fer.opp.model.enums.RouteStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private RouteStatus routeStatus;

	@ManyToOne
	private Neighborhood neighborhood;

	@OneToMany(mappedBy = "container")
	private List<Ping> pings;

	@OneToMany(mappedBy = "container")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "container")
	private List<Emptying> emptyings;

	public Container() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { this.id = id; }

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

	public RouteStatus getRouteStatus() { return routeStatus; }

	public void setRouteStatus(RouteStatus routeStatus) { this.routeStatus = routeStatus; }

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Container)) return false;
		Container container = (Container) o;
		return getId().equals(container.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Container{" +
				"id=" + id +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", pingsSinceEmptied=" + pingsSinceEmptied +
				", routeStatus=" + routeStatus +
				", neighborhood=" + neighborhood +
				", pings=" + pings +
				", favorites=" + favorites +
				", emptyings=" + emptyings +
				'}';
	}

}
