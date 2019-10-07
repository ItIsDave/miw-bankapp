package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBankAccountService {
    @Autowired
    CompanyDao companyDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    SMEUserDao smeUserDao;
    private final static int MAX_ACCOUNTS = 8;


    public AddBankAccountService() {
    }

    public String addBankAccount(Company currentCompany) {
        // bsn is unique but we have set it up as a list
        String message;
        if (MAX_ACCOUNTS > 6) {
            message = "You reached the max account numbers";
        } else {
            BankAccount newBankAccount = new BankAccount();
            newBankAccount.setAccountType("Zakelijk");
            currentCompany.addCompanyAccount(newBankAccount);
            bankAccountDao.save(newBankAccount);
            companyDao.save(currentCompany);
            message = "Success!";
        }
        return message;
    }
}