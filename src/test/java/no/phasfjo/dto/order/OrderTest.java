package no.phasfjo.dto.order;

import no.phasfjo.dto.orderline.OrderLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by phasf on 27.01.2017.
 */
public class OrderTest {

    /*
    TODO
    ALL TESTS WILL FAIL IN THE FUTURE BECAUSE OF THE DATE ISSUES!
     */

    private Validator validator;
    private OrderLine firstOrder;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        firstOrder = new OrderLine();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
        firstOrder = null;
    }

    @Test
    public void nullValueOrderTest() throws Exception {

        Order order = new Order();
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        assertEquals(6, violations.size());
    }

    @Test
    public void invalidValueOrderTest() throws Exception {

        Date creationDate = new Date("11/11/2020");         // Violation! InitDate in the future is not allowed
        Double price = 0d;                                  // Violation ! null value not allowed
        Date paymentDate = new Date("10/10/2016");          // Violation! InitDate in the past is not allowed
        Date deliveryDate = new Date("10/11/2015");         // Violation! InitDate in the past is not allowed
        Integer quantity = 0;                               // Quantity cannot be 0 must be more than 1
        List<OrderLine> listOfThings = new ArrayList<>();  //Violations a empty list!

        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(5, violations.size());
    }

    @Test
    public void validOrderTest() throws Exception {

        Date creationDate = new Date();
        Double price = 10d;
        Date paymentDate = new Date("12/10/2019 20:00:00 GMT");
        Date deliveryDate = new Date("12/12/2019 20:00:00 GMT");
        Integer quantity = 1;

        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setId(1);
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);

        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        order.setId(1);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        assertTrue(violations.isEmpty());
    }

    //TODO test would not like CreationDate crashes all.

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCreationDateTest() throws Exception {

        Date creationDate = new Date("12/20/2026 10:27:00 GMT");    //Violations date In the future @Past @Itemm fail
        Double price = 10d;
        Date paymentDate = new Date("12/10/2019 20:00:00 GMT");
        Date deliveryDate = new Date("12/12/2019 20:00:00 GMT");
        Integer quantity = 1;

        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setId(1);
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);

        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        order.setId(1);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        assertEquals(1, violations.size());
        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("Order{id=1, creationDate=Sun Dec 20 5:27:00 CET 2026, totalAmount=10.0, paymentDate=Sun Dec 10 15:00:00 CET 2017, deliveryDate=Tue Dec 12 15:00:00 EST 2017, quantity=1, orderLines=[OrderLine{id=1, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Eastern Time":
                assertEquals("Order{id=1, creationDate=Sun Dec 20 11:27:00 CET 2026, totalAmount=10.0, paymentDate=Sun Dec 10 21:00:00 CET 2017, deliveryDate=Tue Dec 12 21:00:00 CET 2017, quantity=1, orderLines=[OrderLine{id=1, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central European Time":
                assertEquals("Order{id=1, creationDate=Sun Dec 20 11:27:00 CET 2026, totalAmount=10.0, paymentDate=Tue Dec 10 21:00:00 CET 2019, deliveryDate=Thu Dec 12 21:00:00 CET 2019, quantity=1, orderLines=[OrderLine{id=1, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Standard Time":
                assertEquals("Order{id=1, creationDate=Sun Dec 20 04:27:00 CST 2026, totalAmount=10.0, paymentDate=Sun Dec 10 14:00:00 CST 2017, deliveryDate=Tue Dec 12 14:00:00 CST 2017, quantity=1, orderLines=[OrderLine{id=1, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }
        assertEquals("Dates have to be in chronological order", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPriceTest() throws Exception {

        Date creationDate = new Date();
        Double price = 0d;                                              //Violations price is 0
        Date paymentDate = new Date("12/20/2019 20:00:00 GMT");
        Date deliveryDate = new Date("12/22/2019 18:00:00 GMT");
        Integer quantity = 2;
        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);

        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        assertEquals(1, violations.size());
        assertEquals(price, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }


    // TEST IS CHANGING IF RUNS / CHANGE ORDER -RUNS OK ALONE?

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPaymentDateTest() throws Exception {

        Date creationDate = new Date();
        Double price = 14d;
        Date paymentDate = new Date("11/20/2016 10:10:10 GMT");         // Violations date is after creation date and fail @past test
        Date deliveryDate = new Date("12/13/2019 19:15:00 GMT");
        Integer quantity = 1;

        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setId(1);
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);


        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        order.setId(1);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        assertEquals(2, violations.size());
//        assertEquals("Sun Nov 20 11:10:10 CET 2016", violations.iterator().next().getInvalidValue().toString());
//        assertEquals("must be in the future", violations.iterator().next().getMessage());
        // assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates.message}", violations.iterator().next().getMessageTemplate());
    }

    private String timeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.getTimeZone().getDisplayName();
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDeliveryDateTest() throws Exception {

        Date creationDate = new Date("01/01/2019 10:00:00 GMT");
        Double price = 14d;
        Date paymentDate = new Date("12/12/2019 10:10:10 GMT");
        Date deliveryDate = new Date("11/22/2019 19:15:00 GMT");         // Violations date is before payment date and fail @past test
        Integer quantity = 1;

        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);

        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        order.setId(1);
        assertEquals(1, violations.size());
        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("Order{id=1, creationDate=Sun Jan 01 5:00:00 EST 2017, totalAmount=14.0, paymentDate=Tue Dec 12 5:10:10 EST 2017, deliveryDate=Wed Nov 22 14:15:00 EST 2017, quantity=1, orderLines=[OrderLine{id=0, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central European Time":
                assertEquals("Order{id=1, creationDate=Tue Jan 01 11:00:00 CET 2019, totalAmount=14.0, paymentDate=Thu Dec 12 11:10:10 CET 2019, deliveryDate=Fri Nov 22 20:15:00 CET 2019, quantity=1, orderLines=[OrderLine{id=0, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Eastern Time":
                assertEquals("Order{id=1, creationDate=Sun Jan 01 11:00:00 CET 2017, totalAmount=14.0, paymentDate=Tue Dec 12 11:10:10 CET 2017, deliveryDate=Wed Nov 22 20:15:00 CET 2017, quantity=1, orderLines=[OrderLine{id=0, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            case "Central Standard Time":
                assertEquals("Order{id=1, creationDate=Sun Jan 01 04:00:00 CST 2017, totalAmount=14.0, paymentDate=Tue Dec 12 04:10:10 CST 2017, deliveryDate=Wed Nov 22 13:15:00 CST 2017, quantity=1, orderLines=[OrderLine{id=0, item='Ball', unitPrice=10.0, quantity=1}]}", violations.iterator().next().getInvalidValue().toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone: " + timeZone());
        }
        assertEquals("Dates have to be in chronological order", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ChronologicalDates.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQualityTest() throws Exception {
        Date creationDate = new Date();
        Double price = 14d;
        Date paymentDate = new Date("12/13/2019 10:10:10 GMT");
        Date deliveryDate = new Date("12/14/2019 19:15:00 GMT");
        Integer quantity = 0;                                       //Violations price is 0

        List<OrderLine> listOfThings = new ArrayList<>();
        firstOrder.setItem("Ball");
        firstOrder.setQuantity(1);
        firstOrder.setUnitPrice(10d);
        listOfThings.add(firstOrder);


        Order order = new Order(creationDate, price, paymentDate, deliveryDate, quantity, listOfThings);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(quantity, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }
}
