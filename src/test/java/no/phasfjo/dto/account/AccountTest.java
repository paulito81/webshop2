package no.phasfjo.dto.account;

import no.phasfjo.dto.address.Address;
import no.phasfjo.dto.customer.Customer;
import no.phasfjo.dto.login.Login;
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
public class AccountTest {

    private Validator validator;

    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @After
    public void teardown() throws Exception {
        validator = null;
    }

    //TODO CREATE A CUSTOMER

    @Test
    public void nullValuesAccount() throws Exception {
        Account account = new Account();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
    }

    @Test
    public void validAccountTest() throws Exception {
        Account account = new Account();
        account.setCustomer(new Customer("Per", "Hansen", "test", "90044222", new Date("03/05/1990"), new Address("Innspurten 14", "Oslo", "Oslo", "0663", "Norway")));
        account.setLogin(new Login("Perka", "Piggoo34"));
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertTrue(violations.isEmpty());
    }
}
