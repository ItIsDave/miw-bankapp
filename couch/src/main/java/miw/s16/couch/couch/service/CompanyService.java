package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    CompanyDao companyDao;

}
