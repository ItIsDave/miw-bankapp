package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanDao extends CrudRepository<Loan, Integer> {

public Loan findByLoanId (int loanId);

public List<Loan> findLoansByCustomerBankAccount(BankAccount customerBankAccount);

}
