package hr.fer.opp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String pwdHash;

    @OneToMany(mappedBy = "creator")
    private List<Ping> pings;

    @OneToMany(mappedBy = "owner")
    private List<Favorite> favorites;

    public Person() {
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

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public List<Ping> getPings() {
        return pings;
    }

    public void setPings(List<Ping> pings) {
        this.pings = pings;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "\n\tid=" + id +
                "\n\t, name='" + name + '\'' +
                "\n\t, lastName='" + lastName + '\'' +
                "\n\t, email='" + email + '\'' +
                "\n\t, pwdHash='" + pwdHash + '\'' +
                "\n\t, pings=" + pings +
                '}';
    }

}
