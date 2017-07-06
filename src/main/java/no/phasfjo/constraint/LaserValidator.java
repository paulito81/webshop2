package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class LaserValidator implements ConstraintValidator<Laser, String> {

    /*
    Must start with 6304, 6706, 6771 and contain +12 - 15 digits
     */
    private String laserPattern = "^(6304|6706|6709|6771)[0-9]{12,15}$";

    @Override
    public void initialize(Laser laser) {

    }

    @Override
    public boolean isValid(String laser, ConstraintValidatorContext constraintValidatorContext) {
        return !(laser == null || !laser.matches(laserPattern));
    }
}
