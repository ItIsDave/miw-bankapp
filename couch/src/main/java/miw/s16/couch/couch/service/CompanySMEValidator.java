package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanySMEValidator {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    CompanyDao companyDao;

    public CompanySMEValidator() {
    }

    public boolean validateEmployee(SMEUser formUser) {
        if (formUser.getCompany() == null){
            return false;
        } else {
            return true;
        }
    }

}
