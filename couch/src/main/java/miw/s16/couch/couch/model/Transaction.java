package miw.s16.couch.couch.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaction { //implements Serializable {

    @Id
    @GeneratedValue
    private int transactionId;
    private String description;
    private double amount;
    //@Column(name = "timeStamp")
    private Date transactionDate;
    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="bankAccountId")
    private BankAccount bankAccount;//getIban, can be internal bank account

    // "to" and "from" are reserved key words from MySQL
    private String fromAccount;//getIban, can be internal bank account
    private String toAccount;//getIban, can be internal bank account
    private boolean isPin;
    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name= "loanid")
    private Loan loan;  //betreft de lening indien van toepassing




    public Transaction() {
        this("unknown", 0, new Date(), "", "", new Loan(), false);
    }


    public Transaction(String description, double amount, Date transactionDate,
                       String from, String to, Loan loan, boolean isPin) {
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.fromAccount = from;
        this.toAccount = to;
        this.loan = loan;
        this.isPin = isPin;
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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
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

    public boolean isPin() {
        return isPin;
    }

    public void setPin(boolean isPin) {
        this.isPin = isPin;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + transactionId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate.toString() +
                ", from=" + fromAccount +
                ", to=" + toAccount +
                //", loan=" + loan.toString() +
                ", isPin=" + isPin +
                '}';
    }
}
