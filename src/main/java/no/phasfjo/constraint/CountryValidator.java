package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 22.11.2016.
 */
public class CountryValidator implements ConstraintValidator<Country, String> {

    private String countryPattern = "^[a-zA-Z\\p{javaLetter}\\p{Blank}]{3,30}$";

    @Override
    public void initialize(Country country) {
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {
        return !(country == null || !country.matches(countryPattern));

    }
}
