package miw.s16.couch.couch.model.dao;

//import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

//coding by PvdH

public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {

    public BankAccount findByBankAccountId(int bankAccountId);

    public BankAccount findByIban(String iban);

}
