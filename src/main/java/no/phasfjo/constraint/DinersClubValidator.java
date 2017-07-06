package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class DinersClubValidator implements ConstraintValidator<DinersClub, String> {
    /*
    Must start with 300-305 , 36 or 38 and 14 digits
    (join venture between diners and mastercard start 5 and 16 digits)
     */
    private String dinersclubPattern = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";

    @Override
    public void initialize(DinersClub dinersClub) {

    }

    @Override
    public boolean isValid(String dinersclub, ConstraintValidatorContext constraintValidatorContext) {
        return !(dinersclub == null || !dinersclub.matches(dinersclubPattern));
    }
}
