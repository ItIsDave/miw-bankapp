package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
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
    @Autowired
    private CompanyDao companyDao;

    private List<BankAccount> bankAccounts;
    private List<BankAccount> reportedBankAccounts = new ArrayList<>();//to get the retail user(s) per bank account - BvB
    private List<RetailUser> retailUsers;
    private List<Company> companies;


    public ArrayList<String> balanceTopTen_Client(String role) {
        ArrayList<String> top10List = new ArrayList<>();
        String top10Line = "";

        if (role.equals("Hoofd Particulieren") || role.equals("Hoofd MKB") ) {
            if (role.equals("Hoofd Particulieren")) {
                bankAccounts = bankAccountDao.findTop10ByAccountTypeOrderByBalanceDesc("Particulier");
            }
            if (role.equals("Hoofd MKB")) {
                bankAccounts = bankAccountDao.findTop10ByAccountTypeOrderByBalanceDesc("Zakelijk");
            }
        for (BankAccount b: bankAccounts) {
            top10Line = b.getIBAN() + " " + b.getBalance();
            reportedBankAccounts.clear();
            reportedBankAccounts.add(b);
            if (role.equals("Hoofd Particulieren")) {
                retailUsers = retailUserDao.findRetailUsersByBankAccounts(reportedBankAccounts);
                if (retailUsers.size() == 0){
                    top10Line += " bankaccount not assigned";//for example NL10COUC0423456793 + NL10COUC0323456792
                }
                else {
                    top10Line += " " + retailUsers.get(0).getUserId() +  " " + retailUsers.get(0).getFullName();
                }
            }
            if (role.equals("Hoofd MKB")) {
                companies = companyDao.findByCompanyAccounts(reportedBankAccounts);
                if (companies.size() == 0) {
                    top10Line += " bankaccount not assigned";
                    System.out.println("bankaccount van bedrijf niet gevonden");
                }
                    else {
                        top10Line += " " + companies.get(0).getCompanyName() +  " " + companies.get(0).getCompanyType();
                        }
            }
            top10List.add(top10Line);
        } //einde for each b loop
        } //einde if
        return top10List;
    }


}
