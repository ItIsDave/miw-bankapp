package miw.s16.couch.couch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private int companyId;
    private int chamberOfCommerceId;
    private String companyName;
    @OneToMany(mappedBy="company")
    private List<SMEUser> employees = new ArrayList<>(); // many users can be employees in a company

    public Company() {
        super();
        this.employees = new ArrayList<>();
    }

    public Company(int chamberOfCommerceId, String companyName, List<SMEUser> employees) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.employees = employees;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getChamberOfCommerceId() {
        return chamberOfCommerceId;
    }

    public void setChamberOfCommerceId(int chamberOfCommerceId) {
        this.chamberOfCommerceId = chamberOfCommerceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<SMEUser> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SMEUser> employees) {
        this.employees = employees;
    }
}
