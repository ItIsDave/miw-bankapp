package miw.s16.couch.couch.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class BankUser extends User {

    //@NotEmpty -- it was giving errors
    private String role;
    @OneToMany(mappedBy = "bankAccountTo")
    private List<Transaction> transactionsTo;
    @OneToMany(mappedBy = "accountManager")
    // bank user is responsible for many companies
    private List<Company> companies = new ArrayList<>();


    public BankUser(){
     this(" ", " ");
     this.companies = new ArrayList<>();
    }

    public BankUser(@NotEmpty String userName, @NotEmpty String userPassword) {
        super(userName, userPassword);
    }

    public BankUser(String userName, String password, String role) {
        super(userName, password);
        this.role = role;
    }

    public BankUser(String role, List<Transaction> transactionsTo, List<Company> companies) {
        this.role = role;
        this.transactionsTo = transactionsTo;
        this.companies = companies;
    }


    public List<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(List<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
