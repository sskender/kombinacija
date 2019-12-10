package hr.fer.opp.dto.request;

import hr.fer.opp.model.Neighborhood;

public class AddContainerDTO {
	private Long neighborhoodId;
	private double latitude;
	private double longitude;

	public AddContainerDTO() {
	}

	public AddContainerDTO(Long neighborhoodId, double latitude, double longitude) {
		super();
		this.neighborhoodId = neighborhoodId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(Long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

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

}