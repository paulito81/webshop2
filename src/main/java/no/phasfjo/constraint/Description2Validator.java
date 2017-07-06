package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 17.12.2016.
 */
public class Description2Validator implements ConstraintValidator<Description2, String> {

    private String descriptionPattern = "^[a-zA-a-ZA-Z0-9_\\p{all}]{5,50}";

    @Override
    public void initialize(Description2 description2) {

    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext constraintValidatorContext) {

        return !(description == null || description.equals("") || !description.matches(descriptionPattern));
    }
}
