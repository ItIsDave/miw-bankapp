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
  private String from;//getIBAN, can be internal bank account
//@ManyToOne
  //private BankAccount to;//getIBAN, can be internal bank account
  private String to;//getIBAN, can be internal bank account
//@ManyToOne
     private Loan loan;  //betreft de lening indien van toepassing

     private boolean isPin;

    public Transaction() {
        this(0,"unknown",0,new Date(),"", "" , new Loan(), false);
    }


    public Transaction(int idTransaction, String description, double amount, Date transactionDate,
                       String from, String to, Loan loan, boolean isPin) {
        this.idTransaction = idTransaction;
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.from = from;
        this.to = to;
        this.loan = loan;
        this.isPin = isPin;
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

     public void setTo(String to) {
         this.to = to;
     }

     public String getTo() {
         return to;
     }

     public String getFrom() {
         return from;
     }

     public void setFrom(String from) {
         this.from = from;
     }

     public boolean isPin() {
         return isPin;
     }

     public void setPin(boolean isPin) {
         this.isPin = isPin;
     }

     @Override
     public String toString() {
         return "Transaction{" +
                 "idTransaction=" + idTransaction +
                 ", description='" + description + '\'' +
                 ", amount=" + amount +
                 ", transactionDate=" + transactionDate.toString() +
                 ", from=" + from +
                 ", to=" + to +
                 //", loan=" + loan.toString() +
                 ", isPin=" + isPin +
                 '}';
     }
 }
