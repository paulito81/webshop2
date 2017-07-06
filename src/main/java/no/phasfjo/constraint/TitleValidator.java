package no.phasfjo.constraint;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 17.11.2016.
 */
public class TitleValidator implements ConstraintValidator<Title, String> {


    private String titlePattern = "^[a-zA-a-ZA-Z0-9_\\p{javaLetter}\\p{Blank}\\p{Punct}]{3,50}";


    @Override
    public void initialize(Title title) {

    }

    @Override
    public boolean isValid(String inputTitle, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputTitle == null || !inputTitle.matches(titlePattern));
    }
}
