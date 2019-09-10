package miw.s16.couch.couch.model;

public class BankUser extends User {

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
