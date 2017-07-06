package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 18.11.2016.
 */
public class UsernameValidator implements ConstraintValidator<UserName, String> {

    private String pattern = "^[a-zA-Z0-9._\\p{javaLetter}\\p{javaSpaceChar}}]{3,}$";

    @Override
    public void initialize(UserName userName) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return !(userName == null || !userName.matches(pattern));
    }
}
