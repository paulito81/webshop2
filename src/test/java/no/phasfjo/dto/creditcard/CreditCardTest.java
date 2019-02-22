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
public class CreditCardTest {
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
    public void nullValueCreditCardTest() {
        CreditCard creditCard = new CreditCard();
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard);
        assertEquals(8, violations.size());
    }

    @Test
    public void invalidCreditCardTest() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("A3312345678965433");    //Cannot contain alfa, and have be 15 digits and should start with 34 or 37
        creditCard.setExpiryDate(new Date("2/2/2014")); //Credit paymentCard number is before todays date.
        creditCard.setCvvNumber("12");                  //Cannot contain alfa, and have to be 3 letters
        creditCard.setCardType("abc");                  // Does not match cards from the list
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard);
        assertEquals(4, violations.size());
    }

    @Test
    public void validCreditCardAMEXTest() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("343456789065436");
        creditCard.setExpiryDate(new Date("2/5/2020"));
        creditCard.setCvvNumber("123");
        creditCard.setCardType("AMERICAN_EXPRESS");
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCardNumberNotMatchStartWith99() throws Exception {
        CreditCard creditCard1 = new CreditCard("993456789065490", new Date("12/12/2020 20:00:00 GMT"), "123", "AMERICAN_EXPRESS");
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard1);
        assertEquals(1, violations.size());
        assertEquals("Invalid Amex paymentCard combination mnust start with 34 or 37 and have 15 digits", violations.iterator().next().getMessage());
        assertEquals("993456789065490", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Amex.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDateIsInThePast() throws Exception {
        Date date = new Date("11/26/2016 20:00:00 GMT");
        CreditCard creditCard2 = new CreditCard("343456789065490", date, "123", "AMERICAN_EXPRESS");
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals("Invalid date cannot be in the past.", violations.iterator().next().getMessage());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.DateVal.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCvcHasTwoNumbers() throws Exception {
        CreditCard creditCard3 = new CreditCard("343456789065490", new Date("12/12/2020 20:00:00 GMT"), "12", "AMERICAN_EXPRESS");
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard3);
        assertEquals(1, violations.size());
        assertEquals("Invalid ccv must contain three digits", violations.iterator().next().getMessage());
        assertEquals("12", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Ccv.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCardTitle() throws Exception {
        CreditCard creditCard3 = new CreditCard("343456789065490", new Date("12/12/2020 20:00:00 GMT"), "123", "AMEX");
        Set<ConstraintViolation<CreditCard>> violations = validator.validate(creditCard3);
        assertEquals(1, violations.size());
        assertEquals("Invalid card type is not in the card list", violations.iterator().next().getMessage());
        assertEquals("AMEX", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.CardTypes.message}", violations.iterator().next().getMessageTemplate());
    }
}
