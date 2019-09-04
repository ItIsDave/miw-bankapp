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
//    public void TransactionCalculation(String accountTo, BankAccount bankAccount, Double amount){
//
//        BankAccount acountTo = bankAccountDao.findByIBAN("accountTo");
//
//        if (accountTo != null ){
//            double balance = acountTo.getBalance();
//            acountTo.setBalance(balance + amount);
//            double balanceFrom = bankAccount.getBalance();
//            double newBalance = balanceFrom - amount;
//            if (newBalance < 0 ) {
//                System.out.println("Not enough money left in your account");
//            }
//            else {
//                bankAccount.setBalance(balanceFrom - amount);
//            }
//        }
//        else{
//            System.out.println("User not found");
//        }

    //from test data
    public void TransactionCalculationTest(BankAccount bankAccountTo, BankAccount bankAccount, Double amount) {
        double balance = bankAccountTo.getBalance();
        bankAccountTo.setBalance(balance + amount);
        double balanceFrom = bankAccount.getBalance();
        double newBalance = balanceFrom - amount;
        if (newBalance < 0) {
            System.out.println("Not enough money left in your account");
        } else {
            bankAccount.setBalance(balanceFrom - amount);
            System.out.println("Transaction of " + amount + " successful");
            System.out.println("Your new balance is " + newBalance);
        }
    }

}
