package no.phasfjo.dto.orderline;

import no.phasfjo.dto.item.ItemType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by phasf on 27.01.2017.
 */
public class OrderLine2Test {

    private Validator validator;
    private OrderLine2 testOrder;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        testOrder = new OrderLine2();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
        testOrder = null;
    }
    private String timeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.getTimeZone().getDisplayName();
    }

    @Test
    public void nullValueOrderLin2Test() throws Exception {
        OrderLine2 orderLine = new OrderLine2();
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(8, violations.size());
    }
    @Test
    public void invalidValueOrderLine2Test() throws Exception{
        OrderLine2 orderLine = new OrderLine2(new Date(), new Date(), new Date(), 0d, 0, "");
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(6, violations.size());
    }
    @Test
    public void validOrderLine2Test() throws Exception{
        OrderLine2 orderLine = new OrderLine2(new Date(), new Date("02/27/2020 20:00:00 GMT"), new Date("02/29/2020 20:00:00 GMT"), 1900d, 1, "MACBOOK PRO 2020 10ghz");
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertTrue( violations.isEmpty());
    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCreationDate() throws Exception{
        Date date = new Date("2/2/2021 07:00:00");
        OrderLine2 orderLine = new OrderLine2(date, new Date("02/27/2020 09:00:00"), new Date("02/29/2020 10:00:00"), 1900d, 1, "MACBOOK PRO 2020 10ghz");
        orderLine.setId(1);
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());

        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Tue Feb 02 08:00:00 EST 2021, paymentDate=Thu Feb 27 10:00:00 EST 2020, deliveryDate=Sat Feb 29 11:00:00 EST 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Eastern Time":
                assertEquals("OrderLine2{id=1, creationDate=Tue Feb 02 13:00:00 CET 2021, paymentDate=Thu Feb 27 15:00:00 CET 2020, deliveryDate=Sat Feb 29 16:00:00 CET 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central European Time":
                assertEquals("OrderLine2{id=1, creationDate=Tue Feb 02 07:00:00 CET 2021, paymentDate=Thu Feb 27 09:00:00 CET 2020, deliveryDate=Sat Feb 29 10:00:00 CET 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Tue Feb 02 07:00:00 CST 2021, paymentDate=Thu Feb 27 09:00:00 CST 2020, deliveryDate=Sat Feb 29 10:00:00 CST 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }
        assertEquals("Dates have to be in chronological order2", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates2.message}", violations.iterator().next().getMessageTemplate());
    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPaymentDate() throws Exception{

        OrderLine2 orderLine = new OrderLine2(new Date("2/2/2017 08:00:00"), new Date("02/30/2020 09:00:00"), new Date("02/29/2020 10:00:00"), 1900d, 1, "MACBOOK PRO 2020 10ghz");
        orderLine.setId(1);
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());
        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 09:00:00 EST 2017, paymentDate=Sun Mar 01 10:00:00 EST 2020, deliveryDate=Sat Feb 29 11:00:00 EST 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Eastern Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 14:00:00 CET 2017, paymentDate=Sun Mar 01 15:00:00 CET 2020, deliveryDate=Sat Feb 29 16:00:00 CET 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central European Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 08:00:00 CET 2017, paymentDate=Sun Mar 01 09:00:00 CET 2020, deliveryDate=Sat Feb 29 10:00:00 CET 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 08:00:00 CST 2017, paymentDate=Sun Mar 01 09:00:00 CST 2020, deliveryDate=Sat Feb 29 10:00:00 CST 2020, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }
        assertEquals("Dates have to be in chronological order2", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates2.message}", violations.iterator().next().getMessageTemplate());

    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDeliveryDate() throws Exception{

        OrderLine2 orderLine = new OrderLine2(new Date("2/2/2018 08:00:00"), new Date("02/20/2018 09:00:00"), new Date("02/19/2018 10:00:00"), 1900d, 1, "MACBOOK PRO 2020 10ghz");
        orderLine.setId(1);
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());
        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 09:00:00 EST 2017, paymentDate=Mon Feb 20 10:00:00 EST 2017, deliveryDate=Sun Feb 19 11:00:00 EST 2017, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Eastern Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 14:00:00 CET 2017, paymentDate=Mon Feb 20 15:00:00 CET 2017, deliveryDate=Sun Feb 19 16:00:00 CET 2017, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central European Time":
                assertEquals("OrderLine2{id=1, creationDate=Fri Feb 02 08:00:00 CET 2018, paymentDate=Tue Feb 20 09:00:00 CET 2018, deliveryDate=Mon Feb 19 10:00:00 CET 2018, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Standard Time":
                assertEquals("OrderLine2{id=1, creationDate=Thu Feb 02 08:00:00 CST 2017, paymentDate=Mon Feb 20 09:00:00 CST 2017, deliveryDate=Sun Feb 19 10:00:00 CST 2017, unitPrice=1900.0, quantity=1, item='MACBOOK PRO 2020 10ghz'}", violations.iterator().next().getInvalidValue().toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }
        assertEquals("Dates have to be in chronological order2", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates2.message}", violations.iterator().next().getMessageTemplate());

    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidUnitPrice() throws Exception{
        OrderLine2 orderLine = new OrderLine2(new Date(), new Date("02/27/2020"), new Date("02/29/2020"), 0.5d, 1, "MACBOOK PRO 2020 10ghz");
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());
        assertEquals(0.5, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQuantity() throws Exception{
        OrderLine2 orderLine = new OrderLine2(new Date(), new Date("02/27/2020"), new Date("02/29/2020"), 1900d, 0, "MACBOOK PRO 2020 10ghz");
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());

    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidItemNotInList() throws Exception{
        OrderLine2 orderLine = new OrderLine2(new Date(), new Date("02/27/2020"), new Date("02/29/2020"), 1900d, 1, ItemType.DUMMY_NOT_IN_LIST.toString());
        Set<ConstraintViolation<OrderLine2>> violations = validator.validate(orderLine);
        assertEquals(1, violations.size());
        assertEquals(ItemType.DUMMY_NOT_IN_LIST.toString(), violations.iterator().next().getInvalidValue());
        assertEquals("Invalid item must contain min 3 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Item.message}", violations.iterator().next().getMessageTemplate());
    }
}
