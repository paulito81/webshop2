package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 18.11.2016.
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    /*
    Source:http://www.mkyong.com/regular-expressions/10-java-regular-expression-examples-you-should-know/
    */
    private String emailPattern = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(Email email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !(email == null || !email.matches(emailPattern));
    }
}
