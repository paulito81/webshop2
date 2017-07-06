package no.phasfjo.constraint;


import no.phasfjo.dto.orderline.OrderLine2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 07.01.2017.
 */
public class ChronologicalDatesValidator2 implements ConstraintValidator<ChronologicalDates2, OrderLine2> {

    // Date cannot be null
    // Creation date must be before payment date
    // Payment date must be before delivery date


    @Override
    public void initialize(ChronologicalDates2 chronologicalDates) {

    }

    @Override
    public boolean isValid(OrderLine2 orderLine, ConstraintValidatorContext constraintValidatorContext) {
        return !(orderLine.getCreationDate() == null || orderLine.getPaymentDate() == null || orderLine.getDeliveryDate() == null) && orderLine.getCreationDate().getTime() < orderLine.getPaymentDate().getTime() && orderLine.getPaymentDate().getTime() < orderLine.getDeliveryDate().getTime();

    }
}
