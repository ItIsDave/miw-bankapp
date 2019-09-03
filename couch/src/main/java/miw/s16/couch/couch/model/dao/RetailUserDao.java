package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.RetailUser;

import miw.s16.couch.couch.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

public interface RetailUserDao extends CrudRepository<RetailUser, Integer> {


    public List<RetailUser> findByBsn(int bsn);

    public List<RetailUser> findByLastName(String lastName);

}
