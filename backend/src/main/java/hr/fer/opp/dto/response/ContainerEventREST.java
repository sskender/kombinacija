package hr.fer.opp.dto.response;

import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.model.Emptying;
import hr.fer.opp.model.Ping;

public class ContainerEventREST {
	private Long containerId;
	private String type;
	private String creator;
	private long timestamp;

	public ContainerEventREST(Ping p) {
		this.containerId = p.getId();
		this.type = "PING";
		this.creator = p.getCreator().getEmail();
		this.timestamp = p.getTimestamp();
	}

	public ContainerEventREST(Emptying e) {
		this.containerId = e.getId();
		this.type = "EMPTYING";
		this.creator = e.getWorker().getEmail();
		this.timestamp = e.getTimestamp();
	}

	public Long getContainerId() {
		return containerId;
	}

	public void setContainerId(Long containerId) {
		this.containerId = containerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public static List<ContainerEventREST> convertToREST(List<Ping> pings, List<Emptying> emptyings) {
		List<ContainerEventREST> containerEventREST = new ArrayList<>();
		for (Ping p : pings) {
			containerEventREST.add(new ContainerEventREST(p));
		}
		for (Emptying e : emptyings) {
			containerEventREST.add(new ContainerEventREST(e));
		}
		return containerEventREST;
	}

}
