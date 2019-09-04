package miw.s16.couch.couch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {

    //coding by PvdH
    //variables
    @Id
    @GeneratedValue
   // @Column(name="loanid")
    private int loanID;
    private double initialDebt;
    private String endDate;
    @OneToMany(mappedBy = "loan")
    private List<Transaction> loans = new ArrayList<>();


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

    public List<Transaction> getLoans() {
        return loans;
    }

    public void setLoans(List<Transaction> loans) {
        this.loans = loans;
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
        loans.add(transaction);
    }
//    @Override
//    public String toString() {
//        return "" + loanID;
//    }
//

}
