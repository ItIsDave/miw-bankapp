package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountDao extends CrudRepository<RetailUser, String> {

    public List<BankAccount> findByIBAN(String IBAN);
}
