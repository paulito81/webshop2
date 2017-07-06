package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class MasterCardValidator implements ConstraintValidator<MasterCard, String> {

    /*
    Must start with 5 and contain 14 digits
     */
    private String masterCardPattern = "^5[1-5][0-9]{14}$";
    //"^(?:5[1-5][0-9]\\d|222[1-9]|2[3-6][0-9]\\d|27[01][0-9]|2720)([ \\-]?)\\d{4}\1\\d{4}\\1\\d{4}$";

    @Override
    public void initialize(MasterCard masterCard) {

    }

    @Override
    public boolean isValid(String mastercard, ConstraintValidatorContext constraintValidatorContext) {
        return !(mastercard == null || !mastercard.matches(masterCardPattern));
    }
}
