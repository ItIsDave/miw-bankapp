package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Klanten bekijken
// Als hoofd Particulieren wil ik kunnen zien welke tien
//        particuliere klanten het hoogste saldo op hun rekening hebben staan.
@Service
public class BalanceTopTenQuery {
    private static final int N = 10;
    @Autowired
    BankAccountDao bankAccountDao;
    @Autowired
    RetailUserDao retailUserDao;

    public void printBalanceTopTen(){
//https://en.wikipedia.org/wiki/Java_Persistence_Query_Language
//        public List<Author> getAuthorsByLastName(String lastName) {
//            String queryString = "SELECT a FROM Author a " +
//                    "WHERE a.lastName IS NULL OR LOWER(a.lastName) = LOWER(:lastName)";
//
//            TypedQuery<Author> query = getEntityManager().createQuery(queryString, Author.class);
//            query.setParameter("lastName", lastName);
//            return query.getResultList();
//        }

        List<BankAccount> bankAccounts = bankAccountDao.findTop10ByBalanceOrderByBalanceDesc();
 //       List<BankAccount> report = new ArrayList<>();
 //       int bankAccountsSize = bankAccounts.size();
 //       report.add(bankAccounts.get(bankAccountsSize-1));
 //       report.add(bankAccounts.get(bankAccountsSize-2));
        System.out.println(bankAccounts);
 //       public List<BankAccount> getBankAccountsByBalance(double balance){
 //           String queryString = "SELECT a FROM BankAccount a " +
 //                   "WHERE a.lastName IS NULL OR LOWER(a.lastName) = LOWER(:lastName)";
 //           TypedQuery<Balance> query = getEntityManager().createQuery(queryString, BankAccount.class);
 //           query.setParameter("lastName", lastName);
 //           return query.getResultList();
 //       }
        //    List<RetailUser> retailUsers = retailUserDao.findRetailUsersByBankAccounts(bankAccounts);
//        for ( BankAccount bankAccount : bankAccounts) {
//            System.out.println(bankAccount);
//        }
//        String firstName = "", middleName = "", lastName = "";
//        for ( RetailUser retailUser : retailUsers  ) {
//            firstName = retailUser.getFirstName();
//            middleName = retailUser.getMiddleName();
//            lastName = retailUser.getLastName();
//            System.out.println(firstName + " " + ((middleName != null) ? middleName + " " : "") + lastName);
//        }
    }

}
