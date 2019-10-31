package hr.fer.opp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pings")
public class Ping implements Serializable {

	public static final int EMPTY = 0;
	public static final int FULL = 1;
	public static final int URGENT = 2;


	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private int level;
	@Column(nullable = false)
	private long timestamp;
	@Column
	private String photoPath;
	@ManyToOne
	private Person creator;
	@ManyToOne
	private Container container;


	public Ping(long timestamp, int level, String photoPath, Person creator, Container container) {
		this.timestamp = timestamp;
		this.level = level;
		this.photoPath = photoPath;
		this.creator = creator;
		this.container = container;
	}

	public Ping() {};

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

	public String getPhotoPath() { return photoPath; }
	public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

	public Person getCreator() {
		return creator;
	}
	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Container getContainer() { return container; }
	public void setContainer(Container container) {
		this.container = container;
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
		return "Ping{" +
				"id=" + id +
				", level=" + level +
				", timestamp=" + timestamp +
				", photoPath='" + photoPath + '\'' +
				", creator=" + creator +
				", container=" + container +
				'}';
	}
}
