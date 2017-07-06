package no.phasfjo.dto.orderline;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by phasf on 27.01.2017.
 */
public class OrderlineTest {
    private Validator validator;
    private OrderLine testOrder;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        testOrder = new OrderLine();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
        testOrder = null;
    }

    @Test
    public void nullValueOrderLineTest() throws Exception {
        OrderLine orderLine = new OrderLine();
        Set<ConstraintViolation<OrderLine>> violations = validator.validate(orderLine);

        assertEquals(6, violations.size());
    }

    @Test
    public void invalidOrderLineTest() throws Exception {

        testOrder.setQuantity(0);          //Violations quantity must be more than 1
        testOrder.setItem("1123");         //Violations item must contain lettters
        testOrder.setUnitPrice(0d);        //Violations unit price must be more than 1

        Set<ConstraintViolation<OrderLine>> violations = validator.validate(testOrder);
        assertEquals(3, violations.size());
    }

    @Test
    public void validOrderLineTest() throws Exception {

        testOrder.setId(1);
        testOrder.setQuantity(1);
        testOrder.setItem("Take away pizza");
        testOrder.setUnitPrice(100d);

        Set<ConstraintViolation<OrderLine>> violations = validator.validate(testOrder);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQuantityTest() throws Exception {

        testOrder.setId(1);
        testOrder.setQuantity(-1);              //Violations price is below minimum!
        testOrder.setItem("Football");
        testOrder.setUnitPrice(150d);

        Set<ConstraintViolation<OrderLine>> violations = validator.validate(testOrder);
        assertEquals(1, violations.size());
        assertEquals(testOrder.getQuantity(), violations.iterator().next().getInvalidValue());
        assertEquals("Invalid quantity must be more than 1", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.MinQuantity.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidItemTest() throws Exception {
        testOrder.setId(1);
        testOrder.setQuantity(1);
        testOrder.setItem("1B");              //Violations must contain at least 2 letters and cannot contain one letter and a digi
        testOrder.setUnitPrice(150d);

        Set<ConstraintViolation<OrderLine>> violations = validator.validate(testOrder);
        assertEquals(1, violations.size());
        assertEquals(testOrder.getItem(), violations.iterator().next().getInvalidValue());
        assertEquals("Invalid item must contain min 3 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Item.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidUnitPriceTest() throws Exception {
        testOrder.setId(1);
        testOrder.setQuantity(1);
        testOrder.setItem("Football");
        testOrder.setUnitPrice(1d);           //Violations unit price must be more than 2

        Set<ConstraintViolation<OrderLine>> violations = validator.validate(testOrder);
        assertEquals(1, violations.size());
        assertEquals(testOrder.getUnitPrice(), violations.iterator().next().getInvalidValue());
        assertEquals("Invalid price must be more than 2", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.OrderPrice.message}", violations.iterator().next().getMessageTemplate());
    }


    @Test
    public void shouldRaiseNoConstraintViolationOnCalculatePrice() throws Exception {

        OrderLine orderLine = new OrderLine("Pulverpadder", 12.5d, 1);

        ExecutableValidator methodValidator = validator.forExecutables();
        Method method = OrderLine.class.getMethod("calculatePrice", Double.class);
        Set<ConstraintViolation<OrderLine>> violations = methodValidator.validateParameters(orderLine, method, new Object[]{new Double(4.5)});
        assertEquals(0, violations.size());
    }


    @Test
    public void shouldRaiseViolationsOnCalculateVATValueIsToLow() throws NoSuchMethodException {

        OrderLine orderLine = new OrderLine("Pulverpadder", 12.5d, 1);

        ExecutableValidator methodValidator = validator.forExecutables();
        Method method = OrderLine.class.getMethod("calculatePrice", Double.class);
        Set<ConstraintViolation<OrderLine>> violations = methodValidator.validateParameters(orderLine, method, new Object[]{new Double(1.2)});
        displayContraintViolations(violations);

        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 1.4", violations.iterator().next().getMessage());
        assertEquals(1.2, violations.iterator().next().getInvalidValue());
        assertEquals("{javax.validation.constraints.DecimalMin.message}", violations.iterator().next().getMessageTemplate());
    }

    private void displayContraintViolations(Set<ConstraintViolation<OrderLine>> constraintViolations) {
        for (ConstraintViolation constraintViolation : constraintViolations) {
            System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
                    "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

        }

    }
}
