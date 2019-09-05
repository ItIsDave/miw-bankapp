package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.TransactionDao;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    BankAccountDao bankAccountDao;

    public TransactionService() {
        super();

    }

    // from database
    public String TransactionCalculation(String accountTo, BankAccount bankAccount, Double amount) {

        BankAccount bankAccountTo = bankAccountDao.findByIban("accountTo");

        if (accountTo != null) {
            double balance = bankAccountTo.getBalance();
            bankAccountTo.setBalance(balance + amount);
            double balanceFrom = bankAccount.getBalance();
            double newBalance = balanceFrom - amount;
            if (newBalance < 0) {
                return "Not enough money left in your account";
            } else {
                bankAccount.setBalance(balanceFrom - amount);
                return " Transaction of " + amount + " successful. Your old balance was: " + balanceFrom +
                "Your new balance is " + newBalance;
            }
        } else {
            return "User not found";
        }
    }

    //from test data
    public String TransactionCalculationTest(BankAccount bankAccountTo, BankAccount bankAccount, Double amount) {
        double balance = bankAccountTo.getBalance();
        bankAccountTo.setBalance(balance + amount);
        double balanceFrom = bankAccount.getBalance();
        double newBalance = balanceFrom - amount;
        if (newBalance < 0) {
            return "Not enough money left in your account";
        } else {
            bankAccount.setBalance(balanceFrom - amount);
            return "\nTransaction of " + amount + " successful. \nYour old balance was: " + balanceFrom +
                    "\nYour new balance is " + newBalance;
        }
    }

}
