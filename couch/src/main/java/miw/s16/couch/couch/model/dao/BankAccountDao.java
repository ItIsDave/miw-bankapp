package miw.s16.couch.couch.model.dao;

//import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//coding by PvdH

public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {

    public List<BankAccount> findByIBAN(String iBAN);

}
