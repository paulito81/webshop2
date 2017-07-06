package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class BCGlobalValidator implements ConstraintValidator<BCGlobal, String> {

    /*
    Must start with 6541, 6556 and contain +12 digits
    */
    private String bcglobalPattern = "^(6541|6556)[0-9]{12}$";

    @Override
    public void initialize(BCGlobal bcGlobal) {

    }

    @Override
    public boolean isValid(String bcglobal, ConstraintValidatorContext constraintValidatorContext) {
        return !(bcglobal == null || bcglobal.matches(bcglobalPattern));
    }
}
