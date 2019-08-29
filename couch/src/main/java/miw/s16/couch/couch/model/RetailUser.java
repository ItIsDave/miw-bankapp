package miw.s16.couch.couch.model;

import java.util.ArrayList;

public class RetailUser extends User {

    private int bsn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String adress;
    private int houseNumber;
    private String extension;
    private String zipcode;
    private String city;
    private int phoneNumber;
    private int dateOfBirth;
    private String email;
    private String role;
    private ArrayList <BankAccount> retailRekeningen;


 //   public RetailUser(String userName, String userPassword, int userId, int bsn//, String firstName, String lastName,
 //                     //String adress, int houseNumber, String zipcode, String city,
 //                     //int phoneNumber, int datOfBirth, String email, String role
 //                     ) {
 //       this(userName, userPassword, userId, bsn//, firstName, "", lastName, adress,houseNumber, "",
 //               //zipcode, city, phoneNumber, datOfBirth, email, role
 //   );
 //   }

    public RetailUser(String userName, String userPassword, int userId,
                      int bsn, String firstName, String middleName, String lastName,
                      String adress, int houseNumber, String extension,
                      String zipcode, String city, int phoneNumber,
                      int dateOfBirth, String email, String role) {
        super(userName, userPassword, userId);
        this.bsn = bsn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.adress = adress;
        this.houseNumber = houseNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
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
}


