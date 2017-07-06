package no.phasfjo.dto.login;

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
public class LoginTest {
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
    public void nullValueLoginTest() throws Exception {
        Login login = new Login();
        Set<ConstraintViolation<Login>> violations = validator.validate(login);
        assertEquals(4, violations.size());
    }

    @Test
    public void invalidLoginTest() throws Exception {
        Login login = new Login();
        login.setUsername("a");         //Username: must contain upper,lower,digits 3-15 letters
        login.setPassword("n");         //Password: must contain upper,lower,digits and symbols, min 8 digits
        Set<ConstraintViolation<Login>> violations = validator.validate(login);
        assertEquals(2, violations.size());
    }

    @Test
    public void validLoginTest() throws Exception {
        Login login = new Login();
        login.setPassword("asbcDGE123#$!");  //must have lower,high,symbol,min 8 digits
        login.setUsername("abcDEF144");     //must be at least 3 letters, lower, higher, digits
        Set<ConstraintViolation<Login>> violations = validator.validate(login);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByPasswordIsToShort() {
        Login login = new Login("PerGunnar81", "abE4!L#");
        Set<ConstraintViolation<Login>> violations = validator.validate(login);
        assertEquals(1, violations.size());
        assertEquals("Invalid password must contain, lower, high, min 8 dig and 3 symbols", violations.iterator().next().getMessage());
        assertEquals("abE4!L#", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByUserNameContainsSymbols() {
        Login login2 = new Login("PerGunnar81#", "abE4!L#1");
        Set<ConstraintViolation<Login>> violations = validator.validate(login2);
        assertEquals(1, violations.size());
        assertEquals("Invalid userName must contain min 3 letters", violations.iterator().next().getMessage());
        assertEquals("PerGunnar81#", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.UserName.message}", violations.iterator().next().getMessageTemplate());

    }

}
