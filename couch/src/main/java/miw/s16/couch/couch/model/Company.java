package miw.s16.couch.couch.model;

import miw.s16.couch.couch.model.constraints.kvkNumberDoesNotExistConstraint;

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
    @Min(value = 10000000, message  = "KvK-nummer moet 8 cijfers zijn zonder punten.")
    @Max(value = 99999999,  message  = "KvK-nummer moet 8 cijfers zijn zonder punten.")
    @kvkNumberDoesNotExistConstraint(message = "KvK is al in gebruik.")
    private int chamberOfCommerceId;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyType;
    @NotEmpty
    private String sector;
    @NotEmpty
    private String streetName;
    @NotNull
    @Positive
    private int streetNumber;
    @NotEmpty
    private String zipcode;
    @NotEmpty
    private String city;
    @NotEmpty
    @Size(min= 9, max=11)
    private String phoneNumber;
    @NotEmpty
    @Email
    private String email;
    private int pinCode;
    private boolean hasPin;
    // company has many employees
    @OneToMany(mappedBy="company")
    private List<SMEUser> employees = new ArrayList<>();
    @ManyToMany
    private List<BankAccount> companyAccounts = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BankUser accountManager = new BankUser();

    public Company() {
        super();
    }

    public Company(@NotEmpty String companyName) {
        this.companyName = companyName;
    }



    public Company( int chamberOfCommerceId,String companyName) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
    }


    public Company(int chamberOfCommerceId, String companyName, String companyType) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.companyType = companyType;
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

    public Company(int chamberOfCommerceId, String companyName, String sector, int pinCode, boolean hasPin, List<SMEUser> employees, List<BankAccount> companyAccounts) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.sector = sector;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
    }

    public Company(int chamberOfCommerceId, String companyName,  String companyType,  String sector,  String streetName,  int streetNumber,  String zipcode,  String city,  String phoneNumber, int pinCode, boolean hasPin,  String email) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.companyType = companyType;
        this.sector = sector;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.email = email;
    }


    public Company(int chamberOfCommerceId, String companyName,  String companyType, String sector,  String streetName,  int streetNumber,  String zipcode, String city,  String phoneNumber, int pinCode, boolean hasPin, List<SMEUser> employees, List<BankAccount> companyAccounts, BankUser accountManager, String email) {
        this.chamberOfCommerceId = chamberOfCommerceId;
        this.companyName = companyName;
        this.companyType = companyType;
        this.sector = sector;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.hasPin = hasPin;
        this.employees = employees;
        this.companyAccounts = companyAccounts;
        this.accountManager = accountManager;
        this.email = email;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
