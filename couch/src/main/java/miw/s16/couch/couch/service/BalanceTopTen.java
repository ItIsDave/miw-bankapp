package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceTopTen {


    @Autowired
    private BankAccountDao bankAccountDao;
    @Autowired
    private RetailUserDao retailUserDao;

    private List<BankAccount> bankAccounts;
    private List<BankAccount> reportedBankAccounts = new ArrayList<>();//to get the retail user(s) per bank account - BvB
    private List<RetailUser> retailUsers;

    public void printBalanceTopTen() {

        bankAccounts = bankAccountDao.findTop10ByOrderByBalanceDesc();

        System.out.println("10 klanten met hoogste rekeningsaldo:");
        System.out.println("N.B. bij meerdere rekeninghouders wordt eerste r.h. getoond");
        System.out.println("iban     balance    Achternaam");

            for ( BankAccount bankAccount : bankAccounts ) {
                System.out.print("\n" + bankAccount.getIBAN() + " " + bankAccount.getBalance() + " ");
                reportedBankAccounts.clear();
                reportedBankAccounts.add(bankAccount);
                retailUsers = retailUserDao.findRetailUsersByBankAccounts(reportedBankAccounts);
                if (retailUsers.size() == 0){
                    System.out.print("test bank account not assigned to customer");//f.e. NL10COUC0423456793 + NL10COUC0323456792
                     }
                else {
                    System.out.print(retailUsers.get(0).getLastName());
                }
            }

    }

}
