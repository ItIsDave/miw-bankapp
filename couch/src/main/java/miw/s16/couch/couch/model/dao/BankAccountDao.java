package miw.s16.couch.couch.model.dao;

//import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinTable;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//coding by PvdH

public interface BankAccountDao extends CrudRepository<BankAccount, Integer> {

    public BankAccount findByBankAccountId(int bankAccountId);

    public BankAccount findByIban(String iban);

    //hulp van Lex:
    //@JoinTable(name = "(select * FROM bank_account order by bank_account.balance desc limit 10)" )
    /*@OrderBy(clause = "balance")
    @Size(min=0, max = 10)
    public List<BankAccount> topTen = new ArrayList<BankAccount>() ;*/
/*
    public List<BankAccount> findTop10ByBalance();*/

    public List<BankAccount> findTop10ByOrderByBalanceDesc();

}
