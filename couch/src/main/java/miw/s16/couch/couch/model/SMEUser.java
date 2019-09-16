package miw.s16.couch.couch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SMEUser extends User {


    private int chamberOfCommerceId;
    private String companyName;
    private String  roleEmployee;
    @ManyToMany
    private List<BankAccount> smeAccounts = new ArrayList<>();
    // company employees that need sign in
    @OneToMany(mappedBy = "company")
    private List<User> employees = new ArrayList<>();
    //

    public SMEUser() {
        this("","");
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.smeAccounts = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public SMEUser(int chamberOfCommerceId, String companyName, String roleEmployee, ArrayList<BankAccount> smeAccounts) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.roleEmployee = roleEmployee;
        this.smeAccounts = smeAccounts;
    }

    public SMEUser(int chamberOfCommerceId, String companyName, String roleEmployee, List<BankAccount> smeAccounts, List<User> employees) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.roleEmployee = roleEmployee;
        this.smeAccounts = smeAccounts;
        this.employees = employees;
    }

    public SMEUser(String userName, String password, int companyId, String name, String role){
        super(userName, password);
        this.chamberOfCommerceId = companyId;
        this.companyName = name;
        this.roleEmployee = role;
    }

    public int getChamberOfCommerceId() { return chamberOfCommerceId; }

    public void setChamberOfCommerceId(int chamberOfCommerceId) {
        this.chamberOfCommerceId = chamberOfCommerceId;
    }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getRoleEmployee() { return roleEmployee; }

    public void setRoleEmployee(String roleEmployee) { this.roleEmployee = roleEmployee; }

    public List<BankAccount> getSmeAccounts() { return smeAccounts; }

    public void setSmeAccounts(List<BankAccount> smeAccounts) { this.smeAccounts = smeAccounts; }

    public void addBankAccount(BankAccount bankAccount){
        smeAccounts.add(bankAccount);
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public void addMember(User user){
        employees.add(user); }
}
