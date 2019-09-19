package miw.s16.couch.couch.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class BankUser extends User {

    @NotEmpty
    private String role;

    public BankUser(String userName, String password, String role) {
        super(userName, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
