package miw.s16.couch.couch.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue
    private int companyId;
    @Column(name = "kvkNr", unique = true)
    @NotNull
    @Positive
    @Min(value = 10000000, message  = "KvK-nummer moet 8 cijfers zijn zonder punten.")
    @Max(value = 99999999,  message  = "KvK-nummer moet 8 cijfers zijn zonder punten.")
    private int chamberOfCommerceId;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyType;
    @NotEmpty
    private String sector;
    private int pinCode;
    private boolean hasPin;
    // company has many employees
    @OneToMany(mappedBy="company")
    private List<SMEUser> employees = new ArrayList<>();
    @ManyToMany
    private List<BankAccount> companyAccounts = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private BankUser accountManager = new BankUser();

    public Company() {
        super();
    }

    public Company(int chamberOfCommerceId, String companyName, List<SMEUser> employees) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company(int chamberOfCommerceId, String companyName, String sector, List<SMEUser> employees, List<BankAccount> companyAccounts) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.sector = sector;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
    }

    public Company(int chamberOfCommerceId, @NotEmpty String companyName, String sector, int pinCode, boolean hasPin, List<SMEUser> employees, List<BankAccount> companyAccounts) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.sector = sector;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
    }

    public Company(int chamberOfCommerceId, @NotEmpty String companyName, @NotEmpty String companyLegalName, @NotEmpty String sector, @Positive int pinCode, boolean hasPin, List<SMEUser> employees, List<BankAccount> companyAccounts, BankUser accountManager) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.companyType = companyLegalName;
        this.sector = sector;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
        this.accountManager = accountManager;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isHasPin() {
        return hasPin;
    }

    public void setHasPin(boolean hasPin) {
        this.hasPin = hasPin;
    }

    public List<SMEUser> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SMEUser> employees) {
        this.employees = employees;
    }

    public List<BankAccount> getCompanyAccounts() {
        return companyAccounts;
    }

    public void setCompanyAccounts(List<BankAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }

    public void addCompanyEmployee(SMEUser smeUser){
        employees.add(smeUser);
    }

    public void addCompanyAccount(BankAccount bankAccount){
        companyAccounts.add(bankAccount);
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public BankUser getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(BankUser accountManager) {
        this.accountManager = accountManager;
    }
}
