package no.phasfjo.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by phasf on 27.01.2017.
 */
public class ItemServerConnectionTest {
    // ======================================
    // =             Attributes             =
    // ======================================

    protected static ValidatorFactory validatorFactory;
    protected static Validator validator;

    // ======================================
    // =             LIFECYCLE METHODS      =
    // ======================================

    @BeforeClass
    public static void init() throws ParseException {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    private void displayConstraintsViolations(Set<ConstraintViolation<ItemServerConnection>> constraintViolations) {
        for (ConstraintViolation constraintViolation : constraintViolations) {
            System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() + "." + constraintViolation.getPropertyPath()
                    + " - Invalid value " + constraintViolation.getInvalidValue() + " - An error has accured = " + constraintViolation.getMessage());
        }
    }

    @Test
    public void validTestWithoutAnyConstraintsTest() throws Exception {
        ItemServerConnection itemServer = new ItemServerConnection("http://www.paulswarehouse.com/1", "http://www.paulswarehouse.com/12", "ftp://www.paulswarehouse.com:21");
        Set<ConstraintViolation<ItemServerConnection>> violations = validator.validate(itemServer);
        assertEquals(0, violations.size());
    }

    @Test
    public void shoulRaiseConstraintViolationsCausedByInvalidResourceURL() throws Exception {

        ItemServerConnection itemServer = new ItemServerConnection("dummy", "http://www.paulswarehouse.com/", "ftp://www.paulswarehouse.com:21");
        Set<ConstraintViolation<ItemServerConnection>> violations = validator.validate(itemServer);

        displayConstraintsViolations(violations);
        assertEquals(1, violations.size());
        assertEquals("dummy", violations.iterator().next().getInvalidValue());
        assertEquals("must be a valid URL", violations.iterator().next().getMessage());
        assertEquals("{org.hibernate.validator.constraints.URL.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidItemURL() throws Exception {

        ItemServerConnection itemServer = new ItemServerConnection("http://www.paulswarehouse.com/1", "dummy", "ftp://www.paulswarehouse.com:21");
        Set<ConstraintViolation<ItemServerConnection>> violations = validator.validate(itemServer);

        displayConstraintsViolations(violations);
        assertEquals(1, violations.size());
        assertEquals("dummy", violations.iterator().next().getInvalidValue());
        assertEquals("must be a valid URL", violations.iterator().next().getMessage());
        assertEquals("{org.hibernate.validator.constraints.URL.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidFtpServerURL() throws Exception {

        ItemServerConnection itemServer = new ItemServerConnection("http://www.paulswarehouse.com/1", "http://www.paulswarehouse.com/12", "dummy");
        Set<ConstraintViolation<ItemServerConnection>> violations = validator.validate(itemServer);

        displayConstraintsViolations(violations);
        assertEquals(1, violations.size());
        assertEquals("dummy", violations.iterator().next().getInvalidValue());
        assertEquals("must be a valid URL", violations.iterator().next().getMessage());
        assertEquals("{org.hibernate.validator.constraints.URL.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationInvalidURLs() throws Exception {
        ItemServerConnection itemServer = new ItemServerConnection("dummy1", "dummy2", "dummy3");
        Set<ConstraintViolation<ItemServerConnection>> violations = validator.validate(itemServer);
        displayConstraintsViolations(violations);
        assertEquals(3, violations.size());
    }

    /*
    NOT TO SELF - CANNOT RUN TEST UNLESS @URL DEFAULT ANNOTATION..
     */

    @Test(expected = UnexpectedTypeException.class)
    public void shouldRaiseExceptionAsDateIsNotAURL() {
        ItemServerConnection itemServer = new ItemServerConnection("http://www.paulswarehouse.com/1", "http://www.paulswarehouse.com/12", "ftp://www.paulswarehouse.com:21");
        itemServer.setLastConnectionDate(new Date());
        validator.validate(itemServer, Error.class);
    }
}
