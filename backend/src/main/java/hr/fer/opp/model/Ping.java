package hr.fer.opp.model;

import hr.fer.opp.model.enums.PingLevel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pings")
public class Ping implements Serializable {

	public static final String DEFAULT_PHOTO_PATH = "";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PingLevel level;

	@Column(nullable = false)
	private long timestamp;

	@Column
	private String photoPath;

	@ManyToOne
	private Person creator;

	@ManyToOne
	private Container container;

	public Ping() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { this.id = id; }

	public PingLevel getLevel() {
		return level;
	}

	public void setLevel(PingLevel level) {
		this.level = level;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ping)) return false;
		Ping ping = (Ping) o;
		return getId().equals(ping.getId());
	}

	public boolean businessEquals(PingLevel otherLevel){
		return ((otherLevel.equals(PingLevel.URGENT) || otherLevel.equals(PingLevel.FULL))
						&& (level.equals(PingLevel.FULL) || level.equals(PingLevel.URGENT)))
						|| (otherLevel.equals(PingLevel.EMPTY) && level.equals(PingLevel.EMPTY));
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
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
