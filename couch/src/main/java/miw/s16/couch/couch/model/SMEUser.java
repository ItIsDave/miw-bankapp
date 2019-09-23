package miw.s16.couch.couch.model;

import org.apache.tomcat.util.digester.ArrayStack;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SMEUser extends User {

    private String roleEmployee;
    // one employee belongs to one company
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company = new Company();


    public SMEUser() {
        this("", "");
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
    }


    public SMEUser(String userName, String userPassword, String roleEmployee) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
    }

    public SMEUser(String userName, String userPassword, String roleEmployee, Company company) {
        super(userName, userPassword);
        this.roleEmployee = roleEmployee;
        this.company = company;
    }

    public String getRoleEmployee() {
        return roleEmployee;
    }

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}

