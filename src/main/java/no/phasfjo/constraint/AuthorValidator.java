package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
public class AuthorValidator implements ConstraintValidator<Author, String> {

    private String authorPattern = "^[a-zA-Z\\p{javaLetter}\\p{Blank}]{7,30}";

    @Override
    public void initialize(Author author) {

    }

    @Override
    public boolean isValid(String inputAuthor, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputAuthor == null || !inputAuthor.matches(authorPattern));
    }
}
