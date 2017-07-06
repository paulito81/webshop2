package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by paulhasfjord on 27.11.2016.
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ChronologicalDatesValidator.class)
public @interface ChronologicalDates {
    String message() default "{no.phasfjo.dto.constraint.ChronologicalDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
