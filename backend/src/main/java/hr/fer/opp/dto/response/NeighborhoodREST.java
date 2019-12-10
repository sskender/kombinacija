package hr.fer.opp.dto.response;

import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.model.Neighborhood;

public class NeighborhoodREST {
	private Long id;
	private String name;
	private Double longitude;
	private Double latitude;
	private Integer workerCapacity;

	public NeighborhoodREST(Neighborhood n) {
		this.id = n.getId();
		this.name = n.getName();
		this.longitude = n.getLongitude();
		this.latitude = n.getLatitude();
		this.workerCapacity = n.getWorkerCapacity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getWorkerCapacity() {
		return workerCapacity;
	}

	public void setWorkerCapacity(Integer workerCapacity) {
		this.workerCapacity = workerCapacity;
	}

	public static List<NeighborhoodREST> convertToREST(List<Neighborhood> neighborhoods) {
		List<NeighborhoodREST> neighborhoodsREST = new ArrayList<>();
		for (Neighborhood n : neighborhoods) {
			neighborhoodsREST.add(new NeighborhoodREST(n));
		}
		return neighborhoodsREST;
	}
}
