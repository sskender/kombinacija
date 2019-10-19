package hr.fer.opp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trashcans")
public class TrashCan {

	public static final int FULL = 0;
	public static final int URGENT = 1;
	public static final int OK = 2;

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 100, nullable = false)
	private double locationX;
	@Column(length = 100, nullable = false)
	private double locationY;
	@Column(length = 100, nullable = false)
	private int status;
	@ManyToOne
	private Neighborhood neighborhood;
	@OneToMany(mappedBy = "reference")
	private List<Ping> pings;
	@OneToMany(mappedBy = "trashCan")
	private List<Favorite> favorites;

	public TrashCan(double locationX, double locationY, int status, Neighborhood neighborhood, List<Ping> pings, List<Favorite> favorites) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
		this.status = status;
		this.neighborhood = neighborhood;
		this.pings = pings;
		this.favorites = favorites;
	}

	public Long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getLocationX() {
		return locationX;
	}

	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public double getLocationY() {
		return locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
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
		TrashCan other = (TrashCan) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "TrashCan [id=" + id + ", locationX=" + locationX + ", locationY=" + locationY + ", status=" + status
				+ ", neighborhood=" + neighborhood + ", pings=" + pings + ", favorites=" + favorites + "]";
	}

}
