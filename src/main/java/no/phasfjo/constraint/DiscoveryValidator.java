package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class DiscoveryValidator implements ConstraintValidator<Discovery, String> {

    /*
    Must start with 6011 or 65 and 16 digits
    */
    private String discoveryPattern = "^6(?:011|5[0-9]{2})[0-9]{12}$";

    @Override
    public void initialize(Discovery discovery) {

    }

    @Override
    public boolean isValid(String discovery, ConstraintValidatorContext constraintValidatorContext) {
        return !(discovery == null || !discovery.matches(discoveryPattern));
    }
}
