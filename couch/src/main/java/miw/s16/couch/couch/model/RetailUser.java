package miw.s16.couch.couch.model;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RetailUser extends User {


    @NotNull
    @Min(value = 10000000, message = "BSN moet 8 of 9 cijfers zijn, zonder punten.")  //AMS: aantal posities mag zijn: 8
    @Max(value = 999999999, message = "BSN moet 8 of 9 cijfers zijn, zonder punten.") //AMS: 8 of 9 posities voldoen
    private int bsn;
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String streetName;
    @NotNull
    @Positive
    private int houseNumber;
    private String extension;
    @NotEmpty
    @Pattern(regexp =  "^[1-9][0-9]{3}[ ]?([a-z]|[A-Z]){2}$")
    private String zipcode;
    @NotEmpty
    private String city;
    @NotEmpty
    @Size(min= 9, max=11) //AMS: in de testdata 11 posities nodig
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/(1[9][0-9]{2}|200[0-1])$")
    private String dateOfBirth;
    @NotEmpty
    @Email
    private String email;
    private String role;
    @ManyToMany
    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();


//    public RetailUser() {
//        super();
//    }

    public RetailUser() {
        this("","");
        }

    public RetailUser(String userName, String userPassword) {
        super(userName, userPassword);
        this.bankAccounts = new ArrayList<>();
    }

    public RetailUser(String userName, String userPassword, int bsn, String firstName,
                      String middleName, String lastName,
                      String streetName, int houseNumber, String extension,
                      String zipcode, String city, String phoneNumber,
                      String dateOfBirth, String email, String role) {
        super(userName, userPassword);
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;

    }


    public RetailUser(String userName, String userPassword, int bsn, String firstName, String middleName, String lastName, String streetName, int houseNumber, String extension, String zipcode, String city, String phoneNumber, String dateOfBirth, String email, String role, List<BankAccount> bankAccounts) {
        super(userName, userPassword);
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
        this.bankAccounts = bankAccounts;
    }

    public int getBsn() { return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BankAccount> getBankAccounts() { return bankAccounts; }

    public void setBankAccounts(List<BankAccount> bankAccounts) { this.bankAccounts = bankAccounts; }

    public void addBankAccount(BankAccount bankAccount){
      bankAccounts.add(bankAccount);
    }

//Added by BvB
    public String getFullName(){
       return firstName + " " + ((middleName != null) ? middleName + " " : "") + lastName;
    }

@Override
    public String toString(){
        return "first name" +firstName + " last name" + lastName +"bsn " + bsn;
    }

}

