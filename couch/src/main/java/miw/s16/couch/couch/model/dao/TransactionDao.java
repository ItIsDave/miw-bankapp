package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction, Integer> {


}
