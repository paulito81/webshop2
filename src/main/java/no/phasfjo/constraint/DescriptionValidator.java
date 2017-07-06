package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 17.11.2016.
 */
public class DescriptionValidator implements ConstraintValidator<Description, String> {


    private String descriptionPattern = "^[a-zA-a-ZA-Z0-9_\\p{all}]{10,200}";

    @Override
    public void initialize(Description description) {

    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext constraintValidatorContext) {
        return !(description == null || description.equals("") || !description.matches(descriptionPattern));
    }
}
