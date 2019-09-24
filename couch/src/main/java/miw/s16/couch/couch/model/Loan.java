package miw.s16.couch.couch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan {

    //coding by PvdH / BvB
    //variables
    @Id
    @GeneratedValue
    private int loanId;
    private double initialDebt;
    private double currentDebt;//BvB
    private String endDate;
    private boolean isApproved; //BvB
    @ManyToOne
    private BankAccount customerBankAccount;//BvB
    @ManyToOne
    private BankAccount internalBankAccount;//NLxx COUC 1xxxxx - BvB
    @OneToMany()
    private List<Transaction> transactions;

//chained constructor for new Loan, not approved, no internal bankaccount assigned yet, no currentDebt (will be = initialDebt by initial transaction once approved)
    public Loan(double initialDebt, String endDate, BankAccount customerBankAccount) {
        this(0, initialDebt, 0, endDate, false, customerBankAccount, null, new ArrayList<>());
    }

    //all args constructor, extended by BvB
    public Loan(int loandId, double initialDebt, double currentDebt, String endDate, boolean isApproved, BankAccount customerBankAccount, BankAccount internalBankAccount, List<Transaction> transactions) {
        this.loanId = loanId;
        this.initialDebt = initialDebt;
        this.currentDebt = currentDebt;
        this.endDate = endDate;
        this.isApproved = isApproved;
        this.customerBankAccount = customerBankAccount;
        this.internalBankAccount = internalBankAccount;
        this.transactions = transactions;
    }

    //no args constructor
    public Loan() {
        super();
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
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
    public int getLoanId() { return loanId; }
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

   // new / missing getters and setters -- BvB
    public double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(double currentDebt) {
        this.currentDebt = currentDebt;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public BankAccount getCustomerBankAccount() {
        return customerBankAccount;
    }

    public void setCustomerBankAccount(BankAccount customerBankAccount) {
        this.customerBankAccount = customerBankAccount;
    }

    public BankAccount getInternalBankAccount() {
        return internalBankAccount;
    }

    public void setInternalBankAccount(BankAccount internalBankAccount) {
        this.internalBankAccount = internalBankAccount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "initialDebt=" + initialDebt +
                ", currentDebt=" + currentDebt +
                ", endDate='" + endDate + '\'' +
                ", isApproved=" + isApproved +
                ", customerBankAccount=" + customerBankAccount +
                '}';
    }

}
