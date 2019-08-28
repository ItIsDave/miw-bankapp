package miw.s16.couch.couch.model;

public class SMEUser extends User {

    private int chamberOfCommerceId;
    private String companyName;
    private String  roleEmployee;

    public SMEUser(String userName, String password, int id, int companyId, String name, String role){
        super(userName, password, id);
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

    public void setRoleEmployee(String roleEmployee) {
        this.roleEmployee = roleEmployee;
    }
}
