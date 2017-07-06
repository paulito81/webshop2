package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 22.11.2016.
 */
public class CityValidator implements ConstraintValidator<City, String> {

    private String cityPattern = "^[a-zA-Z\\p{javaLetter}\\p{Blank}]{3,30}";

    @Override
    public void initialize(City city) {

    }

    @Override
    public boolean isValid(String city, ConstraintValidatorContext constraintValidatorContext) {
        return !(city == null || !city.matches(cityPattern));
    }
}
