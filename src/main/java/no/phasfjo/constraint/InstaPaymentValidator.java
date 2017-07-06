package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class InstaPaymentValidator implements ConstraintValidator<InstaPayment, String> {

    private String instapaymentPattern = "^63[7-9][0-9]{13}$";

    @Override
    public void initialize(InstaPayment instaPayment) {

    }

    @Override
    public boolean isValid(String instapayment, ConstraintValidatorContext constraintValidatorContext) {
        return !(instapayment == null || !(instapayment.matches(instapaymentPattern)));
    }
}
