package no.phasfjo.dto.order;

import no.phasfjo.dto.orderline.OrderLine;
import no.phasfjo.dto.orderline.OrderLine2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by phasf on 27.01.2017.
 */
public class Order2Test {

    private Validator validator;
    private OrderLine firstOrder;
    private List<OrderLine2> list;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        firstOrder = new OrderLine();
        list = new ArrayList<>();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
        firstOrder = null;
    }

    @Test
    public void nullValueOrder2Test() throws Exception {
        Order2 order = new Order2();
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(4, violations.size());
    }

    @Test
    public void invalidValueOrder2Test() {

        OrderLine2 orderLine2 = new OrderLine2(new Date(), new Date(), new Date(), 10d, 1, "BOOK");
        list.add(orderLine2);
        Order2 order = new Order2();
        order.setOrderLines(null);
        order.setQuantity(0);
        order.setToDaysDate(null);
        order.setTotalAmount(0d);

        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(4, violations.size());
    }

    @Test
    public void validOrder2Test() throws Exception {
        OrderLine2 orderLine2 = new OrderLine2(new Date(), new Date("03/27/2017"), new Date("03/30/2017"), 10d, 1, "BOOK");
        list.add(orderLine2);
        Order2 order = new Order2(1, 200d, list, new Date());
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQuantity() throws Exception {
        OrderLine2 orderLine2 = new OrderLine2(new Date(), new Date("03/27/2017"), new Date("03/30/2017"), 10d, 1, "BOOK");
        list.add(orderLine2);
        Order2 order = new Order2(0, 200d, list, new Date());
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }
    @Test
    public void shouldRaiseConstrainViolationsCausedByInvalidTotalAmount() throws Exception {
        OrderLine2 orderLine2 = new OrderLine2(new Date(), new Date("03/27/2017"), new Date("03/30/2017"), 10d, 1, "BOOK");
        list.add(orderLine2);
        Order2 order = new Order2(1, -100d, list, new Date());
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        assertEquals(-100.0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidOrderLines() throws Exception {

        Order2 order = new Order2(1, 200d, null, new Date());
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        assertEquals(null, violations.iterator().next().getInvalidValue());
        assertEquals("may not be null", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDate() throws Exception {
        OrderLine2 orderLine2 = new OrderLine2(new Date(), new Date("03/27/2017"), new Date("03/30/2017"), 10d, 1, "BOOK");
        list.add(orderLine2);
        Order2 order = new Order2(1, 200d, list, null);
        Set<ConstraintViolation<Order2>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        assertEquals(null, violations.iterator().next().getInvalidValue());
        assertEquals("may not be null", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.NotNull.message}", violations.iterator().next().getMessageTemplate());

    }

}
