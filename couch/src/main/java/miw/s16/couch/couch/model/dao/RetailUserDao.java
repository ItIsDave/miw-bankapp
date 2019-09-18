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

//BvB    ConverterNotFoundException: No converter found capable of converting from type [miw.s16.couch.couch.model.RetailUser] to type [miw.s16.couch.couch.model.BankAccount]
    public List<BankAccount> findBankAccountsByUserName(String userName);
    //bvB - added for service BalanceTopTenQuery
    public List<RetailUser> findRetailUsersByBankAccounts(List<BankAccount> bankAccounts);

    public List<RetailUser> findByUserName(String userName);

}
