package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PasswordValidator {

    @Autowired
    UserDao userDao;


    public PasswordValidator() {
        super();
    }

    public boolean validateMemberPassword(User formUser) {
        boolean loginOk;
        // user name is unique
        User dbUser = userDao.findByUserName(formUser.getUserName());
        if (dbUser != null) {
            loginOk = dbUser.getUserPassword().equals(formUser.getUserPassword());
        } else {
            loginOk = false;
        }
        return loginOk;
    }

}