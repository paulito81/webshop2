package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class SwitchCardValidator implements ConstraintValidator<SwitchCard, String> {

    /*
    Must start with 4903,4905,4911,4936,6333,6759                +12 digits
                    4903,4905,4911,4936,6333,6759                +14 digits
                    4903,4905,4911,4936,6333,6759                +15 digits
                    564182 +10 digits, 564182 +12 digits, 564182 +13 digits,
                    633110 +10 digits, 633110 +12 digits, 633110 +13 digits
     */
    private String switchcardPattern = "^(4903|4905|4911|4936|6333|6759)[0-9]{12}|(4903|4905|4911|4936|6333|6759)[0-9]{14}|(4903|4905|4911|4936|6333|6759)[0-9]{15}|564182[0-9]{10}|564182[0-9]{12}|564182[0-9]{13}|633110[0-9]{10}|633110[0-9]{12}|633110[0-9]{13}$";

    @Override
    public void initialize(SwitchCard switchCard) {

    }

    @Override
    public boolean isValid(String switchcard, ConstraintValidatorContext constraintValidatorContext) {
        return !(switchcard == null || !switchcard.matches(switchcardPattern));
    }
}
