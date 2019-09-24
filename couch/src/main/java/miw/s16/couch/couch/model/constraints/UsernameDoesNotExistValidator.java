package miw.s16.couch.couch.model.constraints;

import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.constraints.UsernameDoesNotExistConstraint;
import miw.s16.couch.couch.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UsernameDoesNotExistValidator implements ConstraintValidator<UsernameDoesNotExistConstraint, String> {

    @Autowired
    UserDao userDao;

    @Override
    public void initialize(UsernameDoesNotExistConstraint constraint){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(userDao == null){
            return true;
        }
        return userDao.findByUserName(value) == null;
    }
}
