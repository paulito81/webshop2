package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 22.11.2016.
 */
public class StreetValidator implements ConstraintValidator<Street, String> {


    private String streetPattern = "^[a-zA-a-ZA-Z0-9\\p{all}]{10,200}$";

    @Override
    public void initialize(Street street) {

    }

    @Override
    public boolean isValid(String street, ConstraintValidatorContext constraintValidatorContext) {
        return !((street == null) || !street.matches(streetPattern));
    }
}
