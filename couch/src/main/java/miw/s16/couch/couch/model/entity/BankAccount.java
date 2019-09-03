package miw.s16.couch.couch.model.entity;


import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

//coding by PvdH

@Entity
public class BankAccount {

@Id
@GeneratedValue
    private int BankAccountId;
    private String iBAN;
    private double balance;

//    @OneToMany
//    private List<Transaction> transactions;
//    @ManyToMany
//    private List<RetailUser> retailusers;

    //constructors
    public BankAccount () {}

    public BankAccount(String iBAN, double balance) {
        this.iBAN = iBAN;
        this.balance = balance;
//        this.transactions = new ArrayList<>();
//        this.retailusers = new ArrayList<>();
    }

    //getters
    public String getIBAN() { return iBAN; }
    public double getBalance() {return balance;}
//    public void addTransactions (Transaction transaction) {transactions.add(transaction);}
//    public void addRetailUser (RetailUser retailuser) {retailusers.add(retailuser);}

    //setter for changes in balance, transactions and retailusers
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return iBAN;
    }

}
