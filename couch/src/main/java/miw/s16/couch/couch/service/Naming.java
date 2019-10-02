package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Naming {

    @Autowired
    RetailUserDao retailUserDao;

    public String namingAccounts(BankAccount bankAccount){
        // get a string of full names of retailuser(s) of this bank account, which can be more than only the logged in retailuser (BvB)
        final String RETAIL_USER_SPLIT = " en/of ";
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(bankAccount);
        List<RetailUser> retailUsers = retailUserDao.findRetailUsersByBankAccounts(bankAccounts);
        List<String> retailUserFullNames = new ArrayList<>();
        int nRetailUsers = retailUsers.size();
        for (int i = 0; i < nRetailUsers; i++) {
            retailUserFullNames.add(retailUsers.get(i).getFullName());
        }
        StringBuilder retailUserFullNamesSB = new StringBuilder();
        for (int i = 0; i < nRetailUsers; i++) {
            if (i > 0) {
                retailUserFullNamesSB.append(RETAIL_USER_SPLIT);
            }
            retailUserFullNamesSB.append(retailUserFullNames.get(i));
        }
        return retailUserFullNamesSB.toString();
    }

}
