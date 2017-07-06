package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 04.12.2016.
 */
public class MinQuantityValidator implements ConstraintValidator<MinQuantity, Integer> {

    private final int MIN_QUANTITY = 1;

    @Override
    public void initialize(MinQuantity minQuantity) {

    }

    @Override
    public boolean isValid(Integer inputValue, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputValue == null || inputValue == 0 || inputValue < MIN_QUANTITY);
    }
}
