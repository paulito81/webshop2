package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class AmexValidator implements ConstraintValidator<Amex, String> {

    /*
    Must start with 34 or 37 and have 15 digits
     */
    private String amexPattern = "^3[47][0-9]{13}$";

    @Override
    public void initialize(Amex amex) {
    }

    @Override
    public boolean isValid(String amex, ConstraintValidatorContext constraintValidatorContext) {
        return !(amex == null || !amex.matches(amexPattern));
    }
}
