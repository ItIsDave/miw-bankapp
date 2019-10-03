package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyDao  extends CrudRepository<Company, Integer> {

    public Company findBychamberOfCommerceId(int chamberOfCommerceId);

    public List<Company> findByCompanyName(String companyName);

    public List<Company> findByCompanyAccounts(List<BankAccount> list);


}
