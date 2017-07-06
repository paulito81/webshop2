package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class SoloCardValidator implements ConstraintValidator<Solo, String> {

    private String solocardPattern = "^(6334|6767)[0-9]{12}|(6334|6767)[0-9]{14}|(6334|6767)[0-9]{15}$";

    @Override
    public void initialize(Solo solo) {

    }

    @Override
    public boolean isValid(String solocard, ConstraintValidatorContext constraintValidatorContext) {
        return !(solocard == null || !solocard.matches(solocardPattern));
    }
}
