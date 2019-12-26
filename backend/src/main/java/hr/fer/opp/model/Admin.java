package hr.fer.opp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "admins")
public class Admin extends Person implements Serializable {

    public Admin() {
        super();
    }

    public Admin(@NotNull @Size(min = 1, max = 30) String name, @NotNull @Size(min = 1, max = 30) String lastName, @NotNull String email, @NotNull String pwdHash) {
        super(name, lastName, email, pwdHash);
    }

}
