package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by paulhasfjord on 17.12.2016.
 */
@Constraint(validatedBy = Date2Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateVal2 {
    String message() default "{no.phasfjo.dto.constraint.DateVal2.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
