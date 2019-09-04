package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;

import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.TransactionDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.hibernate.loader.plan.exec.internal.AbstractLoadQueryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class HibernateLab {

    @Autowired
    UserDao userDao;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    TransactionDao transactionDao;


    public HibernateLab() {
        super();
    }

    public void dbinit() {
        // to ensure no data duplication
        if (userDao.findByUserPassword("1").size() == 0) {
            // user info for checking if DB is empty
            User johnDoe = new User("John Doe", "1");

            // Inserting user data
            User adamantia = new User("Adamantia", "1234");
            User alet = new User("Alet", "1234");
            User arnout = new User("Arnout", "1234");
            User boudewijn = new User("Boudewijn", "1234");
            User david = new User("David", "1234");
            User patrick = new User("Patrick", "1234");

            // Creating Bank account data
            BankAccount account1 = new BankAccount("NL10COUC0123456790", 80000.00);
            BankAccount account2 = new BankAccount("NL10COUC0223456791", 10000.00);
            BankAccount account3 = new BankAccount("NL10COUC0323456792", 50000.00);
            BankAccount account4 = new BankAccount("NL10COUC0423456793", 80000.00);
            BankAccount account5 = new BankAccount("NL10COUC0523456794", 100000.00);

            // Creating Retail user data
            RetailUser bart = new RetailUser("Bart", "1234",  987654321, "Bart",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");
            RetailUser charlotte = new RetailUser("Charlotte", "1234", 987654322,
                    "Charlotte",  "de", "Witte", "Keizersgracht", 40,
                    "A", "1017DS", "Amsterdam", "690000001", "25-10-1999", "cdv@gmail.com", "Retail");
            RetailUser karin = new RetailUser("Karin", "1234", 987654325,
                    "Karin",  "van", "Dijk", "Prinsengracht", 200,
                    "A", "1017KS", "Amsterdam", "690000801", "25-10-1990", "kvd@gmail.com", "Retail");
            RetailUser jan = new RetailUser("Jan", "1234", 987654326,
                    "Jan",  "", "Bakken", "Herengracht", 100,
                    "C", "1018AC", "Amsterdam", "6901230801", "25-10-1989", "jbakker@gmail.com", "Retail");


            //Creating transaction data
            Date d = new Date();
            // the to and from are strings. For the test we are going to connect each transaction with one of the retail users bank account
            Transaction t1 = new Transaction("verjaardag",100.0,d,"NL82 INGB 0004 2181 41","NL45 ABNA 0976 0833 96",null,false);
            Transaction t2 = new Transaction("verjaardag2",10.0,d,"NL82 INGB 0005 2181 41","NL45 ABNA 0976 0833 97",null,false);
            Transaction t3 = new Transaction("verjaardag3",150.0,d,"NL82 INGB 0006 2181 41","NL45 ABNA 0976 0833 98",null,false);
            Transaction t4 = new Transaction("verjaardag4",2000.0,d,"NL82 INGB 0007 2181 41","NL45 ABNA 0976 0833 99",null,false);

            // confirmation that DB is running
            System.out.println("Creating schema");
            charlotte.addBankAccount(account2);
            bart.addBankAccount(account1);
            jan.addBankAccount(account5);


            bankAccountDao.save(account1);
            bankAccountDao.save(account2);
            bankAccountDao.save(account3);
            bankAccountDao.save(account4);
            bankAccountDao.save(account5);

            transactionDao.save(t1);
            transactionDao.save(t2);
            transactionDao.save(t3);
            transactionDao.save(t4);
            account1.addTransaction(t1);
            account2.addTransaction(t2);
            account2.addTransaction(t3);
            account3.addTransaction(t4);

            // saving to the db
            userDao.save(johnDoe);
            userDao.save(adamantia);
            userDao.save(alet);
            userDao.save(arnout);
            userDao.save(boudewijn);
            userDao.save(david);
            userDao.save(patrick);

            retailUserDao.save(bart);
            retailUserDao.save(karin);
            retailUserDao.save(charlotte);
            retailUserDao.save(jan);












        }

    }

}
