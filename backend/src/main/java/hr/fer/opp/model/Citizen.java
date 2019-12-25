package hr.fer.opp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "citizens")
public class Citizen extends Person implements Serializable {

    public static final int DEFAULT_CITIZEN_REPUTATION = 0;
    public static final int CITIZEN_REPUTATION_INCREASE = 10;
    public static final int CITIZEN_REPUTATION_DECREASE = 15;

    @Column(nullable = false)
    private Integer reputation;

    public Citizen() {
        super();
    }

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
