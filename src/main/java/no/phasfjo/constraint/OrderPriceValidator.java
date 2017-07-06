package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Created by paulhasfjord on 04.12.2016.
 */
public class OrderPriceValidator implements ConstraintValidator<OrderPrice, Double> {

    private Double standardPrice;
    private Double nullPointer;

    @Override
    public void initialize(OrderPrice orderPrice) {
        standardPrice = 2d;
        nullPointer = 0d;
    }

    @Override
    public boolean isValid(Double inputVal, ConstraintValidatorContext constraintValidatorContext) {

        return !(inputVal == null || Objects.equals(inputVal, nullPointer) || inputVal < nullPointer || inputVal < standardPrice);
    }
}
