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
    @ManyToMany
    private List<Company> companies = new ArrayList<>(); // many companies have many employees


    public SMEUser() {
        this("","");
        this.companies = new ArrayList<>();
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.companies = new ArrayList<>();
        this.companyAccounts = new ArrayList<>();
    }

    public SMEUser(String userName, String userPassword, String roleEmployee, List<BankAccount> companyAccounts, List<Company> companies) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.companyAccounts = companyAccounts;
        this.companies = companies;
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

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void addCompany(Company company){
        companies.add(company);
    }
}
