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
        // to ensure no data dublication
        if (userDao.findByUserPassword("1").size() == 0) {
            // user info for checking if DB is empty
            User JohnDoe = new User("John Doe", "1");

            // Inserting user data
            User Adamantia = new User("Adamantia", "1234");
            User Alet = new User("Alet", "1234");
            User Arnout = new User("Arnout", "1234");
            User Boudewijn = new User("Boudewijn", "1234");
            User David = new User("David", "1234");
            User Patrick = new User("Patrick", "1234");
            BankAccount account1 = new BankAccount("NL123445t3d5", 800.00);
            RetailUser Bart = new RetailUser("Bart", "1234",  987654321, "Bart",  "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", 690000000, "25-10-1900", "bart@hva.nl", "Retail");

            // Inserting Bank account data
            BankAccount account2 = new BankAccount("NL123445t345", 100.00);
            Set<BankAccount> accounts = new HashSet<>();
            accounts.add(account1);
            accounts.add(account2);
            RetailUser Charlotte = new RetailUser("Charlotte", "1234", 987654322,
                    "Charlotte",  "de", "Witte", "Keizersgracht", 40, "A", "1017DS", "Amsterdam", 690000001, "25-10-1999", "cdv@gmail.com", "Retail");

            System.out.println("Creating schema");

            // saving to the db
            userDao.save(JohnDoe);
            userDao.save(Adamantia);
            userDao.save(Alet);
            userDao.save(Arnout);
            userDao.save(Boudewijn);
            userDao.save(David);
            userDao.save(Patrick);
            retailUserDao.save(Bart);
            bankAccountDao.save(account1);
            bankAccountDao.save(account2);
            Charlotte.setRetailRekeningen(accounts);
            retailUserDao.save(Charlotte);
        }

    }

}
