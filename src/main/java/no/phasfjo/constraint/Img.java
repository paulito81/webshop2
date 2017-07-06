package no.phasfjo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by paulhasfjord on 18.01.2017.
 */

@Constraint(validatedBy = ImageValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Img {
    String message() default "{no.phasfjo.dto.constraint.Image.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
