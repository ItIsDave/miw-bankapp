package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HibernateLab {

    @Autowired
    UserDao userDao;

    public HibernateLab() {
        super();
    }

    public void dbinit() {
        User Adamantia = new User("Adamantia", "1234");
        User  Alet =new User("Alet", "1234");
        User Arnout = new User("Arnout", "1234");
        User Boudewijn = new User("Boudewijn", "1234");
        User David = new User("David", "1234");
        User Patrick = new User("Patrick", "1234");

        System.out.println("User is aangemaakt met id " + Adamantia.getUserId());

        userDao.save(Adamantia);
        userDao.save(Alet);
        userDao.save(Arnout);
        userDao.save(Boudewijn);
        userDao.save(David);
        userDao.save(Patrick);

    }

    public void hibernateExperiment() {
        System.out.print("Running Hibernate experiment... ");
        System.out.println("OK");
    }
}
