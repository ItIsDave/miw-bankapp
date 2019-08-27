package miw.s16.couch.couch.model.entity;

public class BankAccount {

    //coding by PvdH
    //variables
    private int IBAN;
    private String type;

    //all args constructor
    public BankAccount(int IBAN, String type) {
        this.IBAN = IBAN;
        this.type = type;
    }

    //no args constructor
    public BankAccount() {
    }

    //getters
    public int getIBAN() { return IBAN; }
    public String getType() { return type; }

    //setters are not used, because you can't change a bank account

//    @Override
//    public String toString() {
//        return IBAN;
//    }

}
