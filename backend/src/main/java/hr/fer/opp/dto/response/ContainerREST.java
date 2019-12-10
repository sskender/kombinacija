package hr.fer.opp.dto.response;

import java.util.ArrayList;
import java.util.List;

import hr.fer.opp.model.Container;

public class ContainerREST {
    private Long id;
    private Double latitude;
    private Double longitude;

    public ContainerREST(Container c){
        this.id = c.getId();
        this.latitude = c.getLatitude();
        this.longitude = c.getLongitude();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public static List<ContainerREST> convertToREST(List<Container> containers){
		List<ContainerREST> containersREST = new ArrayList<>();
		for (Container c : containers) {
			containersREST.add(new ContainerREST(c));
		}
		return containersREST;
	}
}
