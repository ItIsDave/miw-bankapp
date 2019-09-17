package miw.s16.couch.couch.model;

import org.apache.tomcat.util.digester.ArrayStack;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SMEUser extends User {

    private String roleEmployee;
    @ManyToMany
    private List<BankAccount> companyAccounts = new ArrayList<>();
    // company employees that need sign in
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company = new Company(); // many companies have many employees


    public SMEUser() {
        this("", "");
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.company = new Company();
        this.companyAccounts = new ArrayList<>();
    }

    public SMEUser(String userName, String userPassword, String roleEmployee, List<BankAccount> companyAccounts, Company company) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.companyAccounts = companyAccounts;
        this.company = company;
    }

    public String getRoleEmployee() {
        return roleEmployee;
    }

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
    }

    public List<BankAccount> getCompanyAccounts() {
        return companyAccounts;
    }

    public void setCompanyAccounts(List<BankAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void addCompanyAccount(BankAccount bankAccount){
        companyAccounts.add(bankAccount);
    }
}

