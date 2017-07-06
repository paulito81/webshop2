package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by paulhasfjord on 10.12.2016.
 */

@Constraint(validatedBy = AllcardValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllCardTypes {
    String message() default "{no.phasfjo.dto.constraint.AllCardTypes.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
