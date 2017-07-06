package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class JCBValidator implements ConstraintValidator<JCB, String> {

    /*
    Must start with 2131 or 1800 and 15 digits. Card start with 35 has 16 digits
     */

    private String jcbPattern = "^(?:2131|1800|35\\d{3})\\d{11}$";

    @Override
    public void initialize(JCB jcb) {

    }

    @Override
    public boolean isValid(String jcb, ConstraintValidatorContext constraintValidatorContext) {
        return !(jcb == null || jcb.matches(jcbPattern));
    }
}
