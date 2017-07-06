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
@Constraint(validatedBy = URL2Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URL2 {
    String message() default "{no.phasfjo.dto.constraint.URL.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String protocol() default "";

    String host() default "";

    int port() default -1;
}
