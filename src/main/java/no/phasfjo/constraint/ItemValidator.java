package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 04.12.2016.
 */
public class ItemValidator implements ConstraintValidator<Item, String> {

    private String itemPattern = "^[a-zA-Z][a-zA-Z0-9\\p{javaLetter}\\p{Blank}]{2,30}";

    @Override
    public void initialize(Item item) {

    }

    @Override
    public boolean isValid(String inputItem, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputItem == null || !inputItem.matches(itemPattern));
    }
}
