package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class CarteBlancheValidator implements ConstraintValidator<CarteBlanche, String> {

    /*
    Must start with 389 and have +11 digits
     */
    private String carteblanchePattern = "^389[0-9]{11}$";

    @Override
    public void initialize(CarteBlanche carteBlanche) {

    }

    @Override
    public boolean isValid(String carteBlanche, ConstraintValidatorContext constraintValidatorContext) {
        return !(carteBlanche == null || !carteBlanche.matches(carteblanchePattern));
    }
}
