package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.hibernate.loader.plan.exec.internal.AbstractLoadQueryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class HibernateLab {

    @Autowired
    UserDao userDao;

    @Autowired
    RetailUserDao retailUserDao;


    public HibernateLab() {
        super();
    }

    public void dbinit() {
        // to ensure no data dublication
        if (userDao.findByUserPassword("1").size() == 0) {
            // user info for checking if DB is empty
            User JohnDoe = new User("John Doe", "1", 10000);
            // Inserting user data
            User Adamantia = new User("Adamantia", "1234", 10);
            User Alet = new User("Alet", "1234", 11);
            User Arnout = new User("Arnout", "1234", 12);
            User Boudewijn = new User("Boudewijn", "1234", 13);
            User David = new User("David", "1234", 14);
            User Patrick = new User("Patrick", "1234", 15);
            RetailUser Huub = new RetailUser("Huub", "1234", 200, 987654321, "Huub", "van", "HvA", "Kalverstraat", 25, "100LA", "Amsterdam", 690123456, "10 December 1910", "huub@hva.nl", "Retail");

            System.out.println("Creating schema");

            // saving to the db
            userDao.save(JohnDoe);
            userDao.save(Adamantia);
            userDao.save(Alet);
            userDao.save(Arnout);
            userDao.save(Boudewijn);
            userDao.save(David);
            userDao.save(Patrick);
            RetailUserDao.save(Huub);
        }

    }

}
