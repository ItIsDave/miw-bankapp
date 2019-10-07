package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfUserValidator {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    RetailUserDao retailUserDao;

    public TypeOfUserValidator() {
    }

    public boolean validateSMEUser(User formUser) {
        List<SMEUser> users = smeUserDao.findByUserName(formUser.getUserName());
       return (users != null && users.size() >= 1);
    }


    public boolean validateRetailUser(User formUser) {
        List<RetailUser> users = retailUserDao.findByUserName(formUser.getUserName());
        return (users != null && users.size() >= 1);
    }
}
