package miw.s16.couch.couch.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SMEUser extends User {


    private int chamberOfCommerceId;
    private String companyName;
    private String  roleEmployee;
    @ManyToMany
    private List<BankAccount> smeAccounts = new ArrayList<>();

    public SMEUser() {
        this("","");
    }

    public SMEUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.smeAccounts = new ArrayList<>();
    }

    public SMEUser(int chamberOfCommerceId, String companyName, String roleEmployee, ArrayList<BankAccount> smeAccounts) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.roleEmployee = roleEmployee;
        this.smeAccounts = smeAccounts;
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
}
