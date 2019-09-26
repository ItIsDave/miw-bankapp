package miw.s16.couch.couch.model.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = kvkNumberDoesNotExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface kvkNumberDoesNotExistConstraint {
    String message() default "KvK nummer bestaat al. ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};



}
