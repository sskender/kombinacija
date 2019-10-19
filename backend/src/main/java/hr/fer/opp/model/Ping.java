package hr.fer.opp.model;

import javax.persistence.*;

@Entity
@Table(name = "pings")
public class Ping {

	public static final int FULL = 0;
	public static final int URGENT = 1;

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 100, nullable = false)
	private int level;
	@Column(length = 100, nullable = false)
	private long timestamp;
	@ManyToOne
	private User creator;
	@ManyToOne
	private TrashCan reference;

	public Ping(int level, User creator, TrashCan reference) {
		super();
		this.level = level;
		this.timestamp = System.currentTimeMillis();
		this.creator = creator;
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public TrashCan getReference() {
		return reference;
	}

	public void setReference(TrashCan reference) {
		this.reference = reference;
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
		Ping other = (Ping) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	@Override
	public String toString() {
		return "Ping [id=" + id + ", level=" + level + ", timestamp=" + timestamp + ", creator=" + creator
				+ ", reference=" + reference + "]";
	}

}
