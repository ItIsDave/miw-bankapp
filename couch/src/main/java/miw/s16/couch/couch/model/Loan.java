package miw.s16.couch.couch.model;

public class Loan {

    //coding by PvdH
    //variables
    private int loanID;
    private double initialDebt;
    private String endDate;

    //all args constructor
    public Loan(int loanID, double initialDebt, String endDate) {
        this.loanID = loanID;
        this.initialDebt = initialDebt;
        this.endDate = endDate;
    }

    //no args constructor
    public Loan() {
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

    @Override
    public String toString() {
        return "" + loanID;
    }


}
