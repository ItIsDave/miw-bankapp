package miw.s16.couch.couch.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import java.util.Date;

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
//     @ManyToOne
     private Loan loan;
     private boolean isPin;

    public Transaction() {
        this(0,"unknown",0,new Date(),new BankAccount(), new BankAccount(), new Loan(), false);
    }

     public Transaction(int idTransaction, String description, double amount, Date transactionDate, String from, String to, Loan loan, boolean isPin) {
         this(idTransaction, description, amount, transactionDate, new BankAccount(from, 0), new BankAccount(to, 0), loan, isPin);
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
