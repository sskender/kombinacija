package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "citizens")
public class Citizen extends Person implements Serializable {


	@Column(nullable = false)
	private Integer reputation;

	public Citizen(int reputation) {
		this.reputation = reputation;
	}

	public Citizen() {};

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		String s = super.toString().replaceFirst("Person", "Citizen");
		return s.replace("}", "\n\t, reputation=" + reputation.toString() + "}");
	}

}
