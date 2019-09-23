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

    @NotEmpty
    private String role;
    @OneToMany(mappedBy = "bankAccountTo")
    private List<Transaction> transactionsTo;
    @OneToMany(mappedBy = "accountManager")
    // bank user is responsible for many companies
    private List<Company> companies = new ArrayList<>();


   // public BankUser(){ super(); }

    // changed because of 'cannot be empty violation' errors
    public BankUser(){
     this("admin", "admin", "admin");
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

    public BankUser(String s, String s1) {
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
