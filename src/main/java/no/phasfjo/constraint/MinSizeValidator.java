package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 18.12.2016.
 */
public class MinSizeValidator implements ConstraintValidator<MinSize, Double> {

    private Double minimum = 0.1;

    @Override
    public void initialize(MinSize minSize) {

    }

    @Override
    public boolean isValid(Double inputsize, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputsize == null || inputsize < minimum);
    }
}
