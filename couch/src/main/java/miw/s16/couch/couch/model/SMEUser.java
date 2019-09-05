package miw.s16.couch.couch.model;

import java.util.ArrayList;

public class SMEUser extends User {

    private int chamberOfCommerceId;
    private String companyName;
    private String  roleEmployee;
    private ArrayList<BankAccount> smeRekeningen = new ArrayList<>();

    public SMEUser(String userName, String password, int companyId, String name, String role){
        super(userName, password);
        this.chamberOfCommerceId = companyId;
        this.companyName = name;
        this.roleEmployee = role;
        BankAccount iban = new BankAccount("NL82 INGB 0004 2181 41", 3300 );
        smeRekeningen.add(iban);
    }

    public int getChamberOfCommerceId() { return chamberOfCommerceId; }

    public void setChamberOfCommerceId(int chamberOfCommerceId) {
        this.chamberOfCommerceId = chamberOfCommerceId;
    }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getRoleEmployee() { return roleEmployee; }

    public void setRoleEmployee(String roleEmployee) { this.roleEmployee = roleEmployee; }

    public ArrayList<BankAccount> getSmeRekeningen() { return smeRekeningen; }

    public void setSmeRekeningen(ArrayList<BankAccount> smeRekeningen) { this.smeRekeningen = smeRekeningen; }

    @Override
    public String toString() {
        return "SMEUser{" +
                "chamberOfCommerceId=" + chamberOfCommerceId +
                ", companyName='" + companyName + '\'' +
                ", roleEmployee='" + roleEmployee + '\'' +
                '}';
    }
}
