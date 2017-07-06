package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by paulhasfjord on 25.11.2016.
 */
@Constraint(validatedBy = CardNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumber {
    String message() default "{no.phasfjo.dto.constraint.CardNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
