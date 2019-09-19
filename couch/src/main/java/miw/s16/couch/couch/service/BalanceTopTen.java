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
    private List<Integer> bankAccountIds;

    private List<RetailUser> retailUsers;



    public void printBalanceTopTen() {

        //bankAccounts = bankAccountDao.topTen;
        //retailUsers = retailUserDao.findRetailUsersByBankAccounts(bankAccounts);

        bankAccounts = bankAccountDao.findTop10ByOrderByBalanceDesc();

        /*for (BankAccount b: bankAccounts ) {
            System.out.println("Het bankAccountId is: " + b.getBankAccountId());
            System.out.println("Het saldo is: " + b.getBalance());
            System.out.println("Het iban is: " + b.getIBAN());
        }*/


        //retailUsers = retailUserDao.findRetailUsersByBankAccounts(bankAccounts);

        for (int index = 0; index < 10 ; index++) {

            //System.out.println("Naam is: " + retailUsers.get(index).getFirstName());
            System.out.println("Het bankAccount IBAN is: " + bankAccounts.get(index).getIBAN());

        }


        /*String firstName = "", middleName = "", lastName = "";

        for (RetailUser retailUser : retailUsers) {
            firstName = retailUser.getFirstName();
            middleName = retailUser.getMiddleName();
            lastName = retailUser.getLastName();
            System.out.println(firstName + " " + ((middleName != null)
                    ? middleName + " " : "") + lastName);
        }*/

    }

}
