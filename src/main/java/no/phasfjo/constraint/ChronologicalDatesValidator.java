package no.phasfjo.constraint;

import no.phasfjo.dto.order.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 27.11.2016.
 * <p>
 * *         APress Book - Beginning Java EE 7 with Glassfish 4
 * http://www.apress.com/
 * http://www.antoniogoncalves.org
 */
public class ChronologicalDatesValidator implements ConstraintValidator<ChronologicalDates, Order> {

    // Date cannot be null
    // Creation date must be before payment date
    // Payment date must be before delivery date

    @Override
    public void initialize(ChronologicalDates chronologicalDates) {

    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {

        return !(order.getCreationDate() == null || order.getPaymentDate() == null || order.getDeliveryDate() == null) && order.getCreationDate().getTime() < order.getPaymentDate().getTime() && order.getPaymentDate().getTime() < order.getDeliveryDate().getTime();

    }
}
