package hr.fer.opp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "admins")
public class Admin extends Person implements Serializable {

    public Admin() {
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.replace("Person", "Admin");
    }
}
