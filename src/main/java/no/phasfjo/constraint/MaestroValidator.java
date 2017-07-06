package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class MaestroValidator implements ConstraintValidator<Maestro, String> {

    /*
    Must start with 5018, 5020, 5038,6304, 6759,6761,6763 and contain + 8 - 15 digits
     */

    private String maestroPattern = "^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$";

    @Override
    public void initialize(Maestro maestro) {

    }

    @Override
    public boolean isValid(String maestro, ConstraintValidatorContext constraintValidatorContext) {
        return !(maestro == null || !maestro.matches(maestroPattern));
    }
}
