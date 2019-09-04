//package miw.s16.couch.couch.service;//package miw.s16.couch.couch.service;
//
//import miw.s16.couch.couch.model.RetailUser;
//import miw.s16.couch.couch.model.dao.RetailUserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RetailUserOverview {
//
//    @Autowired
//    RetailUserDao retailUserDao;
//
//    public RetailUserOverview() {
//        super();
//    }
//
//    public String bankAccount(RetailUser retailUser) {
//        RetailUser user1 = retailUserDao.findByUserName(retailUser.getUserName());
//        String account = user1.getBankAccount();
//        return account;
//    }
//}
