package hr.fer.opp.dto;

public class RegisterDTO {
    private String name;
    private String lastName;
    private String email;
    private String pwd;

    public RegisterDTO() {
    }

    public RegisterDTO(String name, String lastName, String email, String pwd) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pwd = pwd;
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
}
