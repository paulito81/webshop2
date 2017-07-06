package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 16.11.2016.
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^+&=])(?=\\S+$).{8,}$";

    @Override
    public void initialize(Password password) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return !(password == null || !password.matches(passwordPattern));
        //if( password == null)
        //    return false;
/*
        boolean containsUpperCase = password.matches(".*([A-Z]).*");
        boolean containsLowerCase = password.matches(".*([a-z]).*");
        boolean containsNumber = password.matches(".*([0-9]).*");
        return (containsUpperCase && containsLowerCase && containsNumber);
        */
    }
}
