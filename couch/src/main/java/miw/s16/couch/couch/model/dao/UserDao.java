package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {

    public User findByUserName(String userName); // username must be unique
    public List<User> findByUserPassword(String userPassword);


}
