package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 24.11.2016.
 */
public class PageNumberValidator implements ConstraintValidator<PageNumber, Integer> {

    private String integerPattern = "^[0-9]\\d{1,4}";

    @Override
    public void initialize(PageNumber pageNumber) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return !(integer == null || !integer.toString().matches(integerPattern));

    }
}
