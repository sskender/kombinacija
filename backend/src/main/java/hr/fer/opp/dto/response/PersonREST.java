package hr.fer.opp.dto.response;

import hr.fer.opp.model.Employee;
import hr.fer.opp.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonREST {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String role;

    public PersonREST(Person person, String role) {
        this.id = person.getId();
        this.name = person.getName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.role = role;
    }

    public static List<PersonREST> convertToREST(List<Employee> persons) {
        List<PersonREST> personsREST = new ArrayList<>();
        for (Person p : persons) {
            personsREST.add(new PersonREST(p, "employee"));
        }
        return personsREST;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}