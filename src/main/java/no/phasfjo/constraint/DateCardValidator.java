package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class DateCardValidator implements ConstraintValidator<DateVal, Date> {

    private Date toDaysDate = new Date();

    @Override
    public void initialize(DateVal dateVal) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return !(date == null || !date.after(toDaysDate));
    }
}
