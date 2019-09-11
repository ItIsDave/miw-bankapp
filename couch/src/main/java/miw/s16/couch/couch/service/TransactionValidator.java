package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionValidator {


    @Autowired
    BankAccountDao bankAccountDao;

    public TransactionValidator() {
        super();
    }

    public boolean validateTransaction(String accountFrom, String accountTo) {
        boolean transactionOk;
        BankAccount bankAccountTo = bankAccountDao.findByIban(accountTo);
        // check if bank account exists
        if (bankAccountTo != null) {
            // check account number to is not the same as from
            if (accountFrom.equals(accountTo)) {
                System.out.println("cannot send to your own account");
                return transactionOk = false;
            }
            return transactionOk = true;
        } else
            System.out.println("bank account does not exist");
        return transactionOk = false;
    }
}

