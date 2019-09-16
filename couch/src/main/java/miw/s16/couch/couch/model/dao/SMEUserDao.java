package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SMEUserDao extends CrudRepository<SMEUser, Integer> {

    public List<SMEUser> findByChamberOfCommerceId(int chamberOfCommerceId);

    public List<SMEUser> findByCompanyName(String lastName);

    public SMEUser findByUserName(String userName);

    public SMEUser findByUserId(int userId);

    public List<BankAccount> findBankAccountsByCompanyName(String companyName);



}
