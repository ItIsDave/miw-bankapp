package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;

import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.hibernate.loader.plan.exec.internal.AbstractLoadQueryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class HibernateLab {

    @Autowired
    UserDao userDao;
    @Autowired
    RetailUserDao retailUserDao;
    @Autowired
    BankAccountDao bankAccountDao;


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
            BankAccount account1 = new BankAccount("NL10100101", 8000.00);
            BankAccount account2 = new BankAccount("NL100101012", 1000.00);
            BankAccount account3 = new BankAccount("NL10019128", 50000);

            // Creating Retail user data
            RetailUser bart = new RetailUser("Bart", "1234",  987654321, "Bart",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");
            RetailUser charlotte = new RetailUser("Charlotte", "1234", 987654322,
                    "Charlotte",  "de", "Witte", "Keizersgracht", 40,
                    "A", "1017DS", "Amsterdam", "690000001", "25-10-1999", "cdv@gmail.com", "Retail");
            RetailUser karin = new RetailUser("Karin", "1234", 987654325,
                    "Karin",  "van", "Dijk", "Prinsengracht", 200,
                    "A", "1017KS", "Amsterdam", "690000801", "25-10-1990", "kvd@gmail.com", "Retail");




            // confirmation that DB is running
            System.out.println("Creating schema");


            // saving to the db
            userDao.save(johnDoe);
            userDao.save(adamantia);
            userDao.save(alet);
            userDao.save(arnout);
            userDao.save(boudewijn);
            userDao.save(david);
            userDao.save(patrick);
            bankAccountDao.save(account1);
            bankAccountDao.save(account2);
            bankAccountDao.save(account3);
//
//         charlotte.addBankAccount(account1);
//         bart.addBankAccount(account2);
//            account1.getRetailusers().add(charlotte);
//            account2.getRetailusers().add(charlotte);

            retailUserDao.save(bart);
            retailUserDao.save(karin);
            retailUserDao.save(charlotte);





        }

    }

}
