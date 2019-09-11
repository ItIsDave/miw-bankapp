package miw.s16.couch.couch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan {

    //coding by PvdH
    //variables
    @Id
    @GeneratedValue
    private int loanID;
    private double initialDebt;
    private String endDate;
    @OneToMany()
    private List<Transaction> transactions = new ArrayList<>();


    //all args constructor
    public Loan(double initialDebt, String endDate) {
        this.initialDebt = initialDebt;
        this.endDate = endDate;
    }

    //no args constructor
    public Loan() {
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    //getters
    public int getLoanID() { return loanID; }
    public double getInitialDebt() { return initialDebt; }
    public String getDate() { return endDate; }

    //setters for changes in initialDebt and endDate
    public void setInitialDebt(double initialDebt) {
        this.initialDebt = initialDebt;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void addLoan(Transaction transaction){
        transactions.add(transaction);
    }
//    @Override
//    public String toString() {
//        return "" + loanID;
//    }
//

}
