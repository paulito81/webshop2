package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class UnionPayValidator implements ConstraintValidator<UnionPay, String> {

    /*
    Must start with 62 and have 14 - 17 digits
     */
    private String unionPayPattern = "^(62[0-9]{14,17})$";

    @Override
    public void initialize(UnionPay unionPay) {

    }

    @Override
    public boolean isValid(String unionpay, ConstraintValidatorContext constraintValidatorContext) {
        return !(unionpay == null || !unionpay.matches(unionPayPattern));
    }
}
