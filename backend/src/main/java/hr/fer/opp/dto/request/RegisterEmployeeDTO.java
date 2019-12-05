package hr.fer.opp.dto.request;

import hr.fer.opp.model.Neighborhood;

public class RegisterEmployeeDTO {
    private String name;
    private String lastName;
    private String email;
    private String pwd;
    private Neighborhood neighborhood;

    public RegisterEmployeeDTO() {
    }

    public RegisterEmployeeDTO(String name, String lastName, String email, String pwd, Neighborhood neighborhood) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwd = pwd;
        this.neighborhood = neighborhood;
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

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

}
