package no.phasfjo.dto.customer;

import no.phasfjo.dto.address.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class CustomerTest {

    private Validator validator;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
    }

    @Test
    public void nullValueCustomerTest() throws Exception {
        Customer customer = new Customer();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(11, violations.size());
    }

    @Test
    public void invalidCustomerTest() throws Exception {
        Customer customer = new Customer("Li", "To", "ton", "problem", new Date("12/14/2027 09:45:00 GMT"), new Address());
        //Firstname must be between 3-50 letters
        //Lastname must be between 3-50 letters
        //Email must have a valid address
        //Must have a number
        //Data cannot be in the future
        //Address is null
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(5, violations.size());
    }

    @Test
    public void validCustomerTest() throws Exception {
        Customer customer = new Customer("Lion", "Tonn", "lion@yahoo.no", "98043839", new Date("2/2/1999 20:00:00 GMT"), new Address("Grønland 40", "Dramen", "Buskerud", "3045", "Norway"));
        customer.setFirstName("Lion");
        customer.setLastName("Tonn");
        customer.setEmail("lion@yahoo.no");
        customer.setPhone("12345678");
        customer.setBirth(new Date("2/2/1999 20:00:00 GMT"));

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraingViolationsCausedByInvalidFirstNameIsToShort() throws Exception {
        Customer customer = new Customer("M", "J. Fox", "j-fox@yahoo.no", "90044789", new Date("2/12/1975 20:00:00 GMT"), new Address("1921 Highway Ave", "Los Angeles", "California", "90068", "USA"));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        assertEquals("Invalid name must be 3 - 50 letters", violations.iterator().next().getMessage());
        assertEquals("M", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid name must be 3 - 50 letters", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidLastNameIsToLong() throws Exception {
        Customer customer1 = new Customer("Micahel", "J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox  J. Fox J. Fox", "j-fox@yahoo.no", "90044789", new Date("2/12/1975 20:00:00 GMT"), new Address("1921 Highway Ave", "Los Angeles", "California", "90068", "USA"));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer1);
        assertEquals(1, violations.size());
        assertEquals("Invalid name must be 3 - 50 letters", violations.iterator().next().getMessage());
        assertEquals("J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox J. Fox  J. Fox J. Fox", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid name must be 3 - 50 letters", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidEmailAdressMissingAlphaAndEndingSignatur() throws Exception {
        Customer customer2 = new Customer("Michael", "J. Fox", "j-fox", "90044789", new Date("2/12/1975 20:00:00 GMT"), new Address("1921 Highway Ave", "Los Angeles", "California", "90068", "USA"));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer2);
        assertEquals(1, violations.size());
        assertEquals("Invalid email must contain a correct address", violations.iterator().next().getMessage());
        assertEquals("j-fox", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Email.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPhoneNumberIsToLong() throws Exception {
        Customer customer3 = new Customer("Michael", "J. Fox", "j-fox@yahoo.no", "900447891", new Date("2/12/1975 20:00:00 GMT"), new Address("1921 Highway Ave", "Los Angeles", "California", "90068", "USA"));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer3);
        assertEquals(1, violations.size());
        assertEquals("Invalid phone number must contain 8 digits", violations.iterator().next().getMessage());
        assertEquals("900447891", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Phone.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidBirthDateIsInTheFuture() throws Exception {
        Date future = new Date("2/12/2075 20:00:00 GMT");
        Customer customer3 = new Customer("Michael", "J. Fox", "j-fox@yahoo.no", "90044789", future, new Address("1921 Highway Ave", "Los Angeles", "California", "90068", "USA"));
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer3);
        assertEquals(1, violations.size());
        assertEquals("Invalid date cannot be in the future", violations.iterator().next().getMessage());
        assertEquals(future, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.InitDate.message}", violations.iterator().next().getMessageTemplate());
    }

}
