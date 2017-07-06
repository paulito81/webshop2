package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class VisaMasterCardValidator implements ConstraintValidator<VisaMaster, String> {

    /*
    Must start with 51-55 or 2221-2270 and has 16 digits
    */
    private String visaMasterPattern = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";

    @Override
    public void initialize(VisaMaster visaMaster) {

    }

    @Override
    public boolean isValid(String visamastercard, ConstraintValidatorContext constraintValidatorContext) {
        return !(visamastercard == null || !visamastercard.matches(visaMasterPattern));
    }
}
