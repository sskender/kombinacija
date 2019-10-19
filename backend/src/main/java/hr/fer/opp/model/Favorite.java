package hr.fer.opp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="favorites")
public class Favorite {
	
	@Id @GeneratedValue
	private Long id;
	@ManyToOne
	private User owner;
	@ManyToOne
	private TrashCan trashCan;
	
	public Favorite(User owner, TrashCan trashCan) {
		this.owner = owner;
		this.trashCan = trashCan;
	}
	
	public Long getId() {
		return id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public TrashCan getTrashCan() {
		return trashCan;
	}
	public void setTrashCan(TrashCan trashCan) {
		this.trashCan = trashCan;
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
		Favorite other = (Favorite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", owner=" + owner + ", trashCan=" + trashCan + "]";
	}

}
