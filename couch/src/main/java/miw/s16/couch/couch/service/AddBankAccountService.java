package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
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
    private final static int MAX_ACCOUNTS = 5;


    public AddBankAccountService() {
    }

    public String addBankAccount(Company currentCompany) {
        // bsn is unique but we have set it up as a list
        String message;
        if (currentCompany.getCompanyAccounts().size() >= MAX_ACCOUNTS) {
            message = "U kunt niet meer dan "+ MAX_ACCOUNTS + " rekeningen hebben. ";
        } else {
            BankAccount newBankAccount = new BankAccount();
            newBankAccount.setAccountType("Zakelijk");
            currentCompany.addCompanyAccount(newBankAccount);
            bankAccountDao.save(newBankAccount);
            companyDao.save(currentCompany);
            message = "Succes!";
        }
        return message;
    }
}
