package miw.s16.couch.couch.model;
import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

//coding by PvdH

@Entity

public class BankAccount {

@Id
@GeneratedValue
    private int BankAccountId;
    private String IBAN;
    private double balance;

//    @OneToMany
//    private List<Transaction> transactions;
    @ManyToMany
    private Set<RetailUser> retailUsers;

    //constructors
    public BankAccount () {}

    public BankAccount(String IBAN, double balance) {
        this.IBAN = IBAN;
        this.balance = balance;
    }

    //getters
    public String getIBAN() { return IBAN; }
    public double getBalance() {return balance;}
//    public void addTransactions (Transaction transaction) {transactions.add(transaction);}

    //setter for changes in balance, transactions and retailusers
    public void setBalance(double balance) { this.balance = balance; }

/*
    IBAN rules: https://nl.wikipedia.org/wiki/International_Bank_Account_Number
    If an account number starts with a 1 it's an internal account, if it starts with a 0 its a customer (SME or Retail) account
    The other starting numbers are never used
*/

    public long generateAccount() {
        final long MAX_ACC_NR = 999999999L;
        final long MIN_ACC_NR = 0L;
        return (long) ((MAX_ACC_NR - MIN_ACC_NR + 1) * Math.random()) + MIN_ACC_NR;       //generate 9 digit acc nr; first digit is always 0
    }

    public int generateCheckDigits(long account) {
        account *= 1000000L;                               //add 'NL' numerical and 00 to end of acc nr according to IBAN rules
        account += 232100L;
        BigInteger bigaccount;                              //convert to BigInteger because long is too short
        bigaccount = BigInteger.valueOf(account);
        bigaccount = bigaccount.add(new BigInteger("122430120000000000000000"));     //add 'COUC' numerical according to IBAN rules
        return (new BigInteger("98").subtract(bigaccount.mod(new BigInteger("97")))).intValue();        //calculate & return check digits
    }

    public String generateAccountAs10digitString(long account){
        String accAsString = Long.toString(account);
        StringBuilder tenDigitAccount = new StringBuilder(accAsString);
        for (int i = 0; i < 10-accAsString.length(); i++){
            tenDigitAccount.insert(0, 0);                   //add zero's in front of account number until it's a 10 digit number
        }
        return tenDigitAccount.toString();
    }

    /*public boolean checkIfIbanAlreadyExists(long account){
        String accountString =  Long.toString(account);
        List<BankAccount> l = bankAccountDao.findDistinctByIBANEndsWith(accountString);     //check if generated account nr aleardy exists
        if (l.size() == 0){
            return false;
        } else {
            return true;
        }
    }

*/

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<Transaction> transactions) {
//        this.transactions = transactions;
//    }

    public Set<RetailUser> getRetailUsers() {
        return retailUsers;
    }

    public void setRetailUsers(Set<RetailUser> retailUsers) {
        this.retailUsers = retailUsers;
    }

    public int getBankAccountId() {
        return BankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        BankAccountId = bankAccountId;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Set<RetailUser> getRetailusers() {
        return retailUsers;
    }

    public void setRetailusers(Set<RetailUser> retailUsers) {
        this.retailUsers = retailUsers;
    }

    public void addRetailUser(RetailUser retailUser){
        retailUsers.add(retailUser);
    }


//    public String generateIban() {
//        long account = generateAccount();                                 //when database is up, check if IBAN already
//        boolean duplicateCheck = checkIfIbanAlreadyExists(account);
//            if (duplicateCheck){
//                return generateIban();                                      //dangerous code: endless loop if (close to) all possible IBANs are generated
//            } else {
//                int checkDigits = generateCheckDigits(account);
//                StringBuilder iban = new StringBuilder();
//                iban.append("NL");
//                if (checkDigits < 10) {                                              //if checkdigits < 10, add a 0 in front
//                    String checkDigitsString = Integer.toString(checkDigits);
//                    checkDigitsString = "0" + checkDigitsString;
//                    iban.append(checkDigitsString);
//                } else {
//                    iban.append(checkDigits);
//                }
//                iban.append("COUC");
//                iban.append(generateAccountAs10digitString(account));
//                return iban.toString();
//            }
//    }
//
//     */


    @Override
    public String toString() {
        return IBAN;
    }

}
