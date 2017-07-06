package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
@Constraint(validatedBy = MasterCardValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MasterCard {
    String message() default "{no.phasfjo.dto.constraint.Mastercard.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
