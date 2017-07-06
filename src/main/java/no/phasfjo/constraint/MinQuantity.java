package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by paulhasfjord on 04.12.2016.
 */
@Constraint(validatedBy = MinQuantityValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinQuantity {
    String message() default "{no.phasfjo.dto.constraint.MinQuantity.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
