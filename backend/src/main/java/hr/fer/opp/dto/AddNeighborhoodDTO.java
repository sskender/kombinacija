package hr.fer.opp.dto;

public class AddNeighborhoodDTO {
	private String name;
	private double centerLatitude;
	private double centerLongitude;
	
	public AddNeighborhoodDTO() {}
	
	public AddNeighborhoodDTO(String name, double centerLatitude, double centerLongitude) {
		super();
		this.name = name;
		this.centerLatitude = centerLatitude;
		this.centerLongitude = centerLongitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCenterLatitude() {
		return centerLatitude;
	}

	public void setCenterLatitude(double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	public double getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLongitude(double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}

}
