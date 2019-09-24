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

    public ArrayList<String> balanceTopTen_Client() {
        ArrayList<String> top10List = new ArrayList<>();
        String top10Line = "";
        bankAccounts = bankAccountDao.findTop10ByOrderByBalanceDesc();
        for (BankAccount b: bankAccounts) {
            //hier komt een opzoekmethode met een bankaccount om de retailUser te vinden
            top10Line = b.getIBAN() + " " + b.getBalance();
            reportedBankAccounts.clear();
            reportedBankAccounts.add(b);
            System.out.println("b is:" + reportedBankAccounts.get(0).getBankAccountId());  //verwacht 6
            retailUsers = retailUserDao.findRetailUsersByBankAccounts(reportedBankAccounts);
            if (retailUsers.size() == 0){
                top10Line += " bankaccount not assigned";//for example NL10COUC0423456793 + NL10COUC0323456792
            }
            else {
                top10Line += " " + retailUsers.get(0).getUserId() +  " " + retailUsers.get(0).getFirstName() +  " " + retailUsers.get(0).getLastName();
            }
            top10List.add(top10Line);

        }

            return top10List;
    }


}
