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
public class CreditCard2Test {
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

        CreditCard2 creditCard = new CreditCard2();
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard);
        assertEquals(7, violations.size());
    }

    @Test
    public void invalidCreditCardTest() {

        CreditcardType creditType;
        CreditCard2 creditCard = new CreditCard2();
        creditCard.setCardNumber("A3312345678965433");                                      //Cannot contain alfa, and have be 15 digits and should start with 34 or 37
        creditCard.setExpiryDate(new Date("2/2/2014"));                                  //Credit paymentCard number is before todays date.
        creditCard.setCvvNumber("12");                                                       //Cannot contain alfa, and have to be 3 letters
        creditCard.setCardType(CreditcardType.DUMMY_NOT_IN_LIST);                            // Does not match cards from the list
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard);
        assertEquals(4, violations.size());
    }

    @Test
    public void validCreditCardAMEXTest() {

        CreditCard2 creditCard = new CreditCard2();
        creditCard.setCardNumber("343456789065436");
        creditCard.setExpiryDate(new Date("2/5/2020"));
        creditCard.setCvvNumber("123");
        creditCard.setCardType(CreditcardType.AMERICAN_EXPRESS);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCardNumberNotMatchStartWith99() throws Exception {

        CreditCard2 creditCard1 = new CreditCard2("993456789065490", new Date("12/12/2018 20:00:00 GMT"), "123", CreditcardType.AMERICAN_EXPRESS);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard1);
        assertEquals(1, violations.size());
        assertEquals("Invalid paymentCard combination check the digits", violations.iterator().next().getMessage());
        assertEquals("993456789065490", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.AllCardTypes.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDateIsInThePast() throws Exception {

        Date date = new Date("11/26/2016 20:00:00 GMT");
        CreditCard2 creditCard2 = new CreditCard2("343456789065490", date, "123", CreditcardType.AMERICAN_EXPRESS);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard2);
        assertEquals(1, violations.size());
        assertEquals("Invalid date cannot be in the past.", violations.iterator().next().getMessage());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.DateVal.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCvcHasTwoNumbers() throws Exception {

        CreditCard2 creditCard3 = new CreditCard2("343456789065490", new Date("12/12/2018 20:00:00 GMT"), "12", CreditcardType.AMERICAN_EXPRESS);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard3);
        assertEquals(1, violations.size());
        assertEquals("Invalid ccv must contain three digits", violations.iterator().next().getMessage());
        assertEquals("12", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Ccv.message}", violations.iterator().next().getMessageTemplate());
    }

    /*
    CREATED AT DUMMY IN LIST TO TEST THE FAuLT -SYSTEM
    */
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidCardTitle() throws Exception {

        CreditCard2 creditCard3 = new CreditCard2("343456789065490", new Date("12/12/2018 20:00:00 GMT"), "123", CreditcardType.DUMMY_NOT_IN_LIST);
        Set<ConstraintViolation<CreditCard2>> violations = validator.validate(creditCard3);
        assertEquals(1, violations.size());
        assertEquals("Invalid credit type, card must be in the list", violations.iterator().next().getMessage());
        assertEquals(CreditcardType.DUMMY_NOT_IN_LIST, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.CreditType.message}", violations.iterator().next().getMessageTemplate());
    }
}
