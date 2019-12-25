package hr.fer.opp.dto.request;

public class RegisterEmployeeDTO {
	private String name;
	private String lastName;
	private String email;
	private String pwd;
	private String oib;
	private Long neighborhoodId;

	public RegisterEmployeeDTO() {
	}

	public RegisterEmployeeDTO(String name, String lastName, String email, String pwd, String oib,
			Long neighborhoodId) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.oib = oib;
		this.neighborhoodId = neighborhoodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public Long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(Long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

}
