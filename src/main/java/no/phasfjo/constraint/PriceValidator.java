package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 17.11.2016.
 */
public class PriceValidator implements ConstraintValidator<Price, Float> {

    private float standardPrice;
    private float nullPointer;

    @Override
    public void initialize(Price price) {
        standardPrice = 2f;
        nullPointer = 0f;
    }

    @Override
    public boolean isValid(Float inputVal, ConstraintValidatorContext constraintValidatorContext) {

        return !(inputVal == null || inputVal == nullPointer || inputVal < standardPrice);
    }
}
