package miw.s16.couch.couch.model.constraints;

import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class bsnDoesNotExistValidator implements ConstraintValidator<bsnDoesNotExistConstraint, Integer> {

    @Autowired
    RetailUserDao retailUserDao;

    @Override
    public void initialize(bsnDoesNotExistConstraint constraint){}

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if(retailUserDao == null){
            return true;
        }
        return retailUserDao.findByBsn(value)==null;
    }

}
