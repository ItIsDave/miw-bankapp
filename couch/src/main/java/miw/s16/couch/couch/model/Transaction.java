package miw.s16.couch.couch.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;

@Entity
public class Transaction implements Comparable<Transaction> { //implements Serializable {

    @Id
    @GeneratedValue
    private int transactionId;
    private String description;
    @NotNull
    @Positive
    private double amount;
    //@Column(name = "timeStamp")
    private Date transactionDate;
    // one
    @ManyToOne
    private BankAccount bankAccount;//getIban, can be internal bank account
    @ManyToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccountTo;
    // "to" and "from" are reserved key words from MySQL
    private String fromAccount;//getIban, can be internal bank account
    @Size(min = 18, max = 18) // temp  to be changed (AT)
    private String toAccount;//getIban, can be internal bank account
    private boolean isPin;


    public Transaction() {
        // this("unknown", 0, new Date(), "", "", new Loan(), false); uitgezet om te voorkomen dat
        // altijd een loan object wordt gemaakt
        super();
        this.transactionDate = new Date();

    }


    public Transaction(String description, double amount, Date transactionDate,
                       String fromAccount, String toAccount,  boolean isPin) {
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.isPin = isPin;
    }

    public Transaction(double amount, Date transactionDate, BankAccount bankAccount, BankAccount bankAccountTo, String description) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.bankAccount = bankAccount;
        this.bankAccountTo = bankAccountTo;
        this.description = description;
    }

    //all args constructor
    public Transaction(String description, double amount, Date transactionDate, BankAccount bankAccount,
                       BankAccount bankAccountTo, String toAccount, String fromAccount, boolean isPin) {
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.bankAccount = bankAccount;
        this.bankAccountTo = bankAccountTo;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.isPin = isPin;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccount getBankAccountTo() {
        return bankAccountTo;
    }

    public void setBankAccountTo(BankAccount bankAccountTo) {
        this.bankAccountTo = bankAccountTo;
    }

    public void setIdTransaction(int idTransaction) {
        this.transactionId = idTransaction;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getIdTransaction() {
        return transactionId;
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

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public boolean getPin() {
        return isPin;
    }

    public void setPin(boolean isPin) {
        this.isPin = isPin;
    }

    public double getBalance(BankAccount bankAccount){
        return bankAccount.getBalance();
    }

    //added by BvB
    public String getTransactionDay(){
        final int NULLYEAR = 1900;
        int year = transactionDate.getYear() + NULLYEAR
        ,   month = transactionDate.getMonth() + 1 //month is like array index (0-11)
        ,   day = transactionDate.getDate();
        return year + "-" + ((month < 10) ? '0' : "") + month + "-" + day;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + transactionId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate.toString() +
                ", transactionDay=" + this.getTransactionDay() +
                ", from=" + fromAccount +
                ", to=" + toAccount +
                ", isPin=" + isPin +
                '}';
    }
    @Override
    public int compareTo(Transaction o) {
        return getTransactionDate().compareTo(o.getTransactionDate());
    }
}
