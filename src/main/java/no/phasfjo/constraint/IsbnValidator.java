package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 17.11.2016.
 */
public class IsbnValidator implements ConstraintValidator<Isbn, String> {

    private String isbnPattern = "^[\\d][0-9]{12}";

    @Override
    public void initialize(Isbn isbn) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return !(number == null || number.equals("") || !number.matches(isbnPattern));
        //  return number != null && !number.equals("") && (number.length() == 13 || !(number.length() < 13 || number.length() > 13));
    }
}
