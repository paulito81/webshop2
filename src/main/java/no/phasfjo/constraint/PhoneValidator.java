package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 18.11.2016.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private String validNorwegianPhone = "^[\\d][0-9]{7}";

    @Override
    public void initialize(Phone validPhone) {

    }

    @Override
    public boolean isValid(String phonenumber, ConstraintValidatorContext constraintValidatorContext) {
        return !(phonenumber == null || !phonenumber.matches(validNorwegianPhone));
    }
}
