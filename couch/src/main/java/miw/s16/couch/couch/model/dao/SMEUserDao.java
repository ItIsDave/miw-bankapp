package miw.s16.couch.couch.model.dao;

import miw.s16.couch.couch.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SMEUserDao extends CrudRepository<SMEUser, Integer> {

    public List<SMEUser> findByUserName(String userName);

    public SMEUser findByUserId(int userId);

    public List<SMEUser> findByCompany(Company company);

    public SMEUser findByBsn(int bsn);


}
