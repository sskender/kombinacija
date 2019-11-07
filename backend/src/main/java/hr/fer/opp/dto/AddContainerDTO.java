package hr.fer.opp.dto;

import hr.fer.opp.model.Neighborhood;

public class AddContainerDTO {
    private Neighborhood neighborhood;
	private double latitude;
	private double longitude;

	public AddContainerDTO() {
	}

	public AddContainerDTO(Neighborhood neighborhood, double latitude, double longitude) {
		super();
		this.neighborhood = neighborhood;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
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
