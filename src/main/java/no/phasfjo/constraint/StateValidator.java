package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 22.11.2016.
 */
public class StateValidator implements ConstraintValidator<State, String> {

    private String statePattern = "^[a-zA-Z\\p{javaLetter}\\p{Blank}]{3,30}";

    @Override
    public void initialize(State state) {

    }

    @Override
    public boolean isValid(String state, ConstraintValidatorContext constraintValidatorContext) {
        return !(state == null || !state.matches(statePattern));
    }
}
