package miw.s16.couch.couch.model;

import java.util.Date;

//inner class until class BankAccount ready
 class BankAccount {
    private String IBAN;
    private double balance;
    private static final String DEFAULT_IBAN = "NLxx XXXX 000x xxxx xx";

     public BankAccount() {
         this(DEFAULT_IBAN);
     }

    public BankAccount(String IBAN) {
        this(IBAN, 0);
    }

     public BankAccount(String IBAN, double balance) {
         this.IBAN = IBAN;
         this.balance = balance;
     }

     public String getIBAN() {
         return IBAN;
     }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                '}';
    }
}

//idem
class Loan{

}

//@Entity
public class Transaction //implements Serializable
 {

//@Id
    //@GeneratedValue
  private int idTransaction;
  private String description;
  private double amount;
//@Column(name = "timeStamp")
  private Date transactionDate;
//@ManyToOne
  private BankAccount from;//getIBAN, can be internal bank account
    //@ManyToOne
  private BankAccount to;//getIBAN, can be internal bank account
     //@ManyToOne
     private Loan loan;
     private boolean isPin;

    public Transaction() {
        this(0,"unknown",0,new Date(),new BankAccount(), new BankAccount(), new Loan(), false);
    }

     public Transaction(int idTransaction, String description, double amount, Date transactionDate, String from, String to, Loan loan, boolean isPin) {
         this(idTransaction, description, amount, transactionDate, new BankAccount(from), new BankAccount(to), loan, isPin);
     }

    public Transaction(int idTransaction, String description, double amount, Date transactionDate, BankAccount from, BankAccount to, Loan loan, boolean isPin) {
        this.idTransaction = idTransaction;
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.from = from;
        this.to = to;
        this.loan = loan;
        this.isPin = isPin;
        this.setBalanceFrom();
        this.setBalanceTo();
    }

     public int getIdTransaction() {
         return idTransaction;
     }

//     public void setIdTransaction(long idTransaction) {
//         this.idTransaction = idTransaction;
//     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public double getAmount() {
         return amount;
     }

     public void setAmount(double amount) {
         this.amount = amount;
     }

     public Date getTransactionDate() {
         return transactionDate;
     }

     public void setTransactionDate(Date transactionDate) {
         this.transactionDate = transactionDate;
     }

     public BankAccount getFrom() { return from; }

     public void setFrom(BankAccount from) {
         this.from = from;
     }

     public BankAccount getTo() {
         return to;
     }

     public void setTo(BankAccount to) {
         this.to = to;
     }

     public boolean isPin() {
         return isPin;
     }

     public void setPin(boolean isPin) {
         this.isPin = isPin;
     }

     private void setBalanceFrom(){
        from.setBalance(from.getBalance() - amount);
     }

     private void setBalanceTo(){
         to.setBalance(to.getBalance() + amount);
     }

     @Override
     public String toString() {
         return "Transaction{" +
                 "idTransaction=" + idTransaction +
                 ", description='" + description + '\'' +
                 ", amount=" + amount +
                 ", transactionDate=" + transactionDate +
                 ", from=" + from +
                 ", to=" + to +
                 ", loan=" + loan +
                 ", isPin=" + isPin +
                 '}';
     }
 }
