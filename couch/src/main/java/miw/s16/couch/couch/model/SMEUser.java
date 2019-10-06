package miw.s16.couch.couch.model;

import org.apache.tomcat.util.digester.ArrayStack;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SMEUser extends User {

    private String roleEmployee;

    // one employee belongs to one company
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company = new Company();
    // SMEUser is not necessarily a retail User
    @NotNull
    @Min(value = 10000000)
    @Max(value = 999999999)
    private int bsn;
    private String firstName;
    private String middleName;
    private String lastName;


    public SMEUser() {
        this("", "");
    }

    public SMEUser(String userName, String password, Company company) {
        super(userName, password);
        this.company = company;
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
    }


    public SMEUser(String userName, String userPassword, String roleEmployee) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
    }

    public SMEUser(String userName, String userPassword, String roleEmployee, int bsn, String firstName, String middleName, String lastName) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

    public SMEUser(String userName, String userPassword, String roleEmployee, Company company) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.company = company;
    }

    public SMEUser(@NotEmpty String userName, @NotEmpty String userPassword, String roleEmployee, Company company, @NotNull @Min(value = 10000000) @Max(value = 999999999) int bsn) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.company = company;
        this.bsn = bsn;
    }

    public SMEUser(String roleEmployee, Company company, @NotNull @Min(value = 10000000) @Max(value = 999999999) int bsn, String firstName, String middleName, String lastName) {
        this.roleEmployee = roleEmployee;
        this.company = company;
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getRoleEmployee() {
        return roleEmployee;
    }

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }
}

