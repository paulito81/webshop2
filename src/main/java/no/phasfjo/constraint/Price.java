package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by phasf on 17.11.2016.
 */
@Constraint(validatedBy = PriceValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "{no.phasfjo.dto.constraint.Price.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
