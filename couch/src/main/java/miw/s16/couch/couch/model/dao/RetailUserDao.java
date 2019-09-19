package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;

import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface RetailUserDao extends CrudRepository<RetailUser, Integer> {


    public List<RetailUser> findByBsn(int bsn);

    public List<RetailUser> findByLastName(String lastName);

    public RetailUser findByUserId(int userId);

   public List<BankAccount> findBankAccountsByUserName(String userName);

    public List<RetailUser> findByUserName(String userName);

    public List<RetailUser> findRetailUsersByBankAccounts(List<BankAccount> bankAccountList);

}
