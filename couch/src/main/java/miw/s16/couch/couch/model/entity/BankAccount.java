package miw.s16.couch.couch.model.entity;

public class BankAccount {

    //coding by PvdH
    //variables
    private int IBAN;
    private String type;
    private double balance;

    //all args constructor
    public BankAccount(int IBAN, String type, double balance) {
        this.IBAN = IBAN;
        this.type = type;
        this.balance = balance;
    }

    //no args constructor
    public BankAccount() {
    }

    //getters
    public int getIBAN() { return IBAN; }
    public String getType() { return type; }
    public double getBalance() {return balance;}

    //setter for changes in balance
    public void setBalance(double balance) {
        this.balance = balance;
    }


//    @Override
//    public String toString() {
//        return IBAN;
//    }

}
