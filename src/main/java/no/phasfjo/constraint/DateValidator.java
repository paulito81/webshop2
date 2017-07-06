package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by phasf on 17.11.2016.
 */
public class DateValidator implements ConstraintValidator<InitDate, java.util.Date> {


    private java.util.Date toDaysDate;

    @Override
    public void initialize(InitDate validInitDate) {

        toDaysDate = new java.util.Date();
    }

    @Override
    public boolean isValid(java.util.Date inputDate, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputDate == null || inputDate.after(toDaysDate));
    }

}