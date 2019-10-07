package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AddOrDeleteEmployeeService {

    @Autowired
    CompanyDao companyDao;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    SMEUserDao smeUserDao;

    public AddOrDeleteEmployeeService() {
        super();
    }


    public String addEmployee(int bsn, Company company, String role) {
        // bsn is unique but we have set it up as a list
        String feedback;
        if (smeUserDao.findByBsn(bsn) != null) {
            feedback = "Couch klant niet beschikbaar";
        } else if (retailUserDao.findByBsn(bsn) != null) {
            RetailUser retailsUser = retailUserDao.findByBsn(bsn);
            SMEUser newEmployee = new SMEUser(retailsUser.getUserName() + "Z", "1234", role, bsn, retailsUser.getFirstName(), retailsUser.getMiddleName(), retailsUser.getLastName());
            newEmployee.setCompany(company);
            smeUserDao.save(newEmployee);
            companyDao.save(company);
            feedback = "Succes! Medewerker toegevoegd";
        } else {
            feedback = "Couch klant niet gevonden";
        }
        return feedback;
    }
}
