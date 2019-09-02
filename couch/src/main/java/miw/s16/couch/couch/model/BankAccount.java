package miw.s16.couch.couch.model;

public class BankAccount {

    //coding by PvdH
    //variables
    private String IBAN;
//    private String type;
    private double balance;

    //all args constructor
    public BankAccount(String IBAN, //String type,
                        double balance) {
        this.IBAN = IBAN;
 //       this.type = type;
        this.balance = balance;
    }

    //no args constructor
    public BankAccount() {
    }

    //getters
    public String getIBAN() { return IBAN; }
//    public String getType() { return type; }
    public double getBalance() {return balance;}

    //setter for changes in balance
    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return IBAN;
    }

}
