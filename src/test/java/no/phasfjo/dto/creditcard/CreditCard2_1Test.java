package no.phasfjo.dto.creditcard;

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
 * Created by phasf on 27.01.2017.
 */
public class CreditCard2_1Test {
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
    public void nullValueCreditCardTest() throws Exception {

        CreditCard2 creditCard2 = new CreditCard2();

        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(7, violations.size());
    }

    @Test
    public void invalidCreditCardTest() throws IllegalArgumentException {

        CreditCard2 creditCard2 = new CreditCard2("13049439049", new Date("12/9/2015 20:00:00"), "44", null);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(4, violations.size());
    }

    @Test
    public void validCreditCardTest() throws Exception {

        CreditCard2 creditCard2 = new CreditCard2("344545656533423", new Date("12/9/2020 20:00:00"), "434", CreditcardType.AMERICAN_EXPRESS);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCardNumberIsTooLong() throws Exception {

        CreditCard2 creditCard2 = new CreditCard2();
        creditCard2.setCardNumber("34454565653342300");
        creditCard2.setExpiryDate(new Date("12/9/2020 20:00:00"));
        creditCard2.setCvvNumber("434");
        creditCard2.setCardType(CreditcardType.AMERICAN_EXPRESS);

        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals("34454565653342300", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid paymentCard combination check the digits", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.AllCardTypes.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidExpiredDateIsOutOfDate() throws Exception {

        Date date = new Date(("12/9/2016 10:00:00"));
        CreditCard2 creditCard2 = new CreditCard2();
        creditCard2.setCardNumber("344545656533423");
        creditCard2.setExpiryDate(date);
        creditCard2.setCvvNumber("434");
        creditCard2.setCardType(CreditcardType.AMERICAN_EXPRESS);

        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid date cannot be in the past.", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.DateVal.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCCVNumberIsToLong() throws Exception {
        Date date = new Date(("12/9/2026 10:00:00"));
        CreditCard2 creditCard2 = new CreditCard2();
        creditCard2.setCardNumber("344545656533423");
        creditCard2.setExpiryDate(date);
        creditCard2.setCvvNumber("43444");
        creditCard2.setCardType(CreditcardType.AMERICAN_EXPRESS);

        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals("43444", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid ccv must contain three digits", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Ccv.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCreditcardTypeIsNull() throws Exception {
        Date date = new Date(("12/9/2026 10:00:00"));
        CreditCard2 creditCard2 = new CreditCard2();
        creditCard2.setCardNumber("344545656533423");
        creditCard2.setExpiryDate(date);
        creditCard2.setCvvNumber("434");
        creditCard2.setCardType(null);

        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals(null, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid credit type, card must be in the list", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.CreditType.message}", violations.iterator().next().getMessageTemplate());
    }
}
