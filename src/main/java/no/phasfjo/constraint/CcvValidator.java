package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 25.11.2016.
 */
public class CcvValidator implements ConstraintValidator<Ccv, String> {

    private String ccvPattern = "^\\p{javaDigit}{3}";

    @Override
    public void initialize(Ccv ccv) {

    }

    @Override
    public boolean isValid(String ccv, ConstraintValidatorContext constraintValidatorContext) {
        return !(ccv == null || !ccv.matches(ccvPattern));
    }
}
