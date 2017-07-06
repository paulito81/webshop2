package no.phasfjo.constraint;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class VisaCardValidator implements ConstraintValidator<VisaCard, String> {

    /*
    Must start with 4 and has 13 digits. (old paymentCard 13 digits)
    */
    private String visaCardPattern = "^4[0-9]{12}(?:[0-9]{3})?$";

    @Override
    public void initialize(VisaCard visaCard) {

    }

    @Override
    public boolean isValid(String visacard, ConstraintValidatorContext constraintValidatorContext) {
        return !(visacard == null || !visacard.matches(visaCardPattern));
    }
}
