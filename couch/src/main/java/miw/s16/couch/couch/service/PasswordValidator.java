//package miw.s16.couch.couch.service;
//
//import miw.s16.couch.couch.model.User;
//import miw.s16.couch.couch.model.dao.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class PasswordValidator {
//
//    @Autowired
//    UserDao userDao;
//
//    public PasswordValidator() {
//        super();
//    }
//
//    public boolean validateMemberPassword(User formUser) {
//        boolean loginOk;
//        List<User> dbUsers = userDao.findByName(formUser.getUserName());
//        if (dbUsers.size() == 1) {
//            User dbUser = dbUsers.get(0);
//            loginOk = dbUser.getUserPassword().equals(formUser.getUserPassword());
//        } else {
//            loginOk = false;
//        }
////        return loginOk;
//    }
//
//}