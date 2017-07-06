package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by paulhasfjord on 17.12.2016.
 */
public class Date2Validator implements ConstraintValidator<DateVal2, Date> {

    private Date todaysDate;

    @Override
    public void initialize(DateVal2 dateVal2) {

        todaysDate = new Date();
    }

    @Override
    public boolean isValid(Date inputDate, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputDate == null || inputDate.after(todaysDate));
    }
}
