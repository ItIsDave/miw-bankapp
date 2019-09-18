package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Loan;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.TransactionDao;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.util.Date;

// By AT
@Service
public class TransactionService {

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    BankAccountDao bankAccountDao;


    public TransactionService() {
        super();

    }

    public String TransactionCalculation(String accountTo, BankAccount bankAccount, Double amount,
                                         Date transactionDate, String description, Boolean isPin) {

        if (accountTo.equals(bankAccount.getIBAN())){
            return "U kunt geen geld overmaken naar uw eigen bankrekening";
        }
        BankAccount bankAccountTo = bankAccountDao.findByIban(accountTo);
        if (bankAccountTo != null) {
            double balance = bankAccountTo.getBalance();
            bankAccountTo.setBalance(balance + amount);
            double balanceFrom = bankAccount.getBalance();
            double newBalance = balanceFrom - amount;
            if (newBalance < 0) {
                return "Overboeking mislukt. Saldo niet toereikend.";
            } else {
                bankAccount.setBalance(balanceFrom - amount);
                // save changed in DB
                Transaction transaction = new Transaction(description, amount, transactionDate, bankAccount, bankAccountTo, accountTo, bankAccount.getIBAN(), isPin);
                bankAccountDao.save(bankAccountTo);
                bankAccountDao.save(bankAccount);
                // message for testing
                System.out.println("Voordat transaction is gevuld is transaction: " +
                        transaction);
                transactionDao.save(transaction);
                return "\nBedankt!\n<small>Uw transactie van " + amount + " euro was succesvol. \nUw saldo was: " + balanceFrom +
                        "\nUw huidige saldo is " + newBalance + " euro.</small>";
            }
        } else {
            return "Overboeking mislukt. Bank rekening niet gevonden.";
        }
    }


}
