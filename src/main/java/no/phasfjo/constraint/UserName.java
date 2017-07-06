package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by phasf on 18.11.2016.
 */
@Constraint(validatedBy = UsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "{no.phasfjo.dto.constraint.UserName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
