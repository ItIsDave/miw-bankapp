package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankUserDao extends CrudRepository<BankUser,Integer> {

    public BankUser findByUserName(User user);

    public List<BankUser> findByUserName(String userName);


    public List<BankUser> findAllByRole(String role);

}
