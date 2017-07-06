package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by paulhasfjord on 07.01.2017.
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ChronologicalDatesValidator2.class)
public @interface ChronologicalDates2 {
    String message() default "{no.phasfjo.dto.constraint.ChronologicalDates2.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
