package miw.s16.couch.couch.model.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = bsnDoesNotExistValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface bsnDoesNotExistConstraint {
    String message() default "BSN bestaat al in klantenbestand.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
