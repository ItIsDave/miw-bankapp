package miw.s16.couch.couch.model;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
@Configuration
@EnableAutoConfiguration
@Entity
@Table( name = "RetailUser" )
//@PrimaryKeyJoinColumn(name="userId")
public class RetailUser extends User {
    private int bsn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String streetName;
    private int houseNumber;
    private String extension;
    private String zipcode;
    private String city;
    private int phoneNumber;
    private String dateOfBirth;
    private String email;
    private String role;
    private ArrayList <BankAccount> retailRekeningen = new ArrayList<>();


    public RetailUser(){

    }

    public RetailUser(String userName, String userPassword, int roleId) {
        super(userName, userPassword);
    }

    public RetailUser(String userName, String userPassword, int bsn, String firstName,
                      String middleName, String lastName,
                      String streetName, int houseNumber, String extension,
                      String zipcode, String city, int phoneNumber,
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


//    public RetailUser(int bsn, String firstName, String middleName, String lastName, String streetName, int houseNumber, String extension, String zipcode, String city, int phoneNumber, String dateOfBirth, String email, String role, ArrayList<BankAccount> retailRekeningen) {
//        this.bsn = bsn;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.streetName = streetName;
//        this.houseNumber = houseNumber;
//        this.extension = extension;
//        this.zipcode = zipcode;
//        this.city = city;
//        this.phoneNumber = phoneNumber;
//        this.dateOfBirth = dateOfBirth;
//        this.email = email;
//        this.role = role;
//        this.retailRekeningen = retailRekeningen;
//    }

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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    public ArrayList<BankAccount> getRetailRekeningen() { return retailRekeningen; }

    public void setRetailRekeningen(ArrayList<BankAccount> retailRekeningen) { this.retailRekeningen = retailRekeningen; }

    public void addBankAccount(BankAccount bankAccount){
      retailRekeningen.add(bankAccount);
    }
}

