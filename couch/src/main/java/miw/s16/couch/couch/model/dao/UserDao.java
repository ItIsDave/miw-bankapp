package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {

    public List<User> findByUserName(String userName);
    public List<User> findByUserPassword(String userPassword);
}
