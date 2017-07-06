package no.phasfjo.dto.address;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class AddressTest {

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
    public void nullValueAddressTest() throws Exception {
        Address address = new Address();
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertEquals(9, violations.size());
    }

    @Test
    public void invalidAddressTest() throws Exception {
        Address address = new Address();
        address.setStreet("SannFran");   // Street size [8  "Must be more than 8 letters"
        address.setCity("B");           // City [3-30]      "Must be 3 - 30 letters long"
        address.setState("M");          // State [3-30]     "Must be 3 - 30 letters long and cannot contain number"
        address.setZipcode("133");      // Zipcode [4-5]    "Must be 4 or 5 digits long"
        address.setCountry("1");        // Country [3-30]   "Must be 3 - 30 letters long and cannot contain number"
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertEquals(5, violations.size());
    }

    @Test
    public void validAddressTest() throws Exception {

        Address address = new Address("Florida street 201", "Florida", "Florida", "33130", "USA");
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseNoConstraintViolation() throws Exception {

        Address address = new Address("Grønland 40", "Dramen", "Buskerud", "3045", "Norway");
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidStreet() {
        Address address0 = new Address("G", "Drammen", "Buskerud", "3045", "Norway");         //violation street contains 1 letter
        Set<ConstraintViolation<Address>> violations = validator.validate(address0);
        assertEquals(1, violations.size());
        assertEquals("Invalid street must contain letters and numbers", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Street.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCity() {
        Address address1 = new Address("Grønland 40", "1Drammen", "Buskerud", "3045", "Norway");        //violation city contains digits
        Set<ConstraintViolation<Address>> violations = validator.validate(address1);
        assertEquals(1, violations.size());
        assertEquals("invalid city name must contain 3-30 letters and no digits", violations.iterator().next().getMessage());
        assertEquals("1Drammen", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.City.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidState() {
        Address address2 = new Address("Grønland 40", "Drammen", "B!usk1rud", "3045", "Norway");        //violations state contains digits and other values
        Set<ConstraintViolation<Address>> violations = validator.validate(address2);
        assertEquals(1, violations.size());
        assertEquals("Invalid state must contain 3-30 letters and no digits", violations.iterator().next().getMessage());
        assertEquals("B!usk1rud", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.State.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidZipCode() {
        Address address3 = new Address("Grønland 40", "Drammen", "Buskerud", "300", "Norway");      //violations zip code is less than 4 digits , 5 digits
        Set<ConstraintViolation<Address>> violations = validator.validate(address3);
        assertEquals(1, violations.size());
        assertEquals("300", violations.iterator().next().getInvalidValue());
        assertEquals("invalid zipcode must be 4 or 5 digits", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.ZipCode.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCauseByInvalidCountry() {
        Address address4 = new Address("Grønland 40", "Drammen", "Buskerud", "3045", "No");      //Violtions country name is less than 3 letters.
        Set<ConstraintViolation<Address>> violations = validator.validate(address4);
        assertEquals(1, violations.size());
        assertEquals("No", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid country must contain 3-30 letters and no digits", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Country.message}", violations.iterator().next().getMessageTemplate());

    }
}
