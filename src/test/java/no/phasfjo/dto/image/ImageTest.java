package no.phasfjo.dto.image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
public class ImageTest {

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
    public void nullValueImageTest() throws Exception {
        Image image = new Image();
        Set<ConstraintViolation<Image>> violations = validator.validate(image);
        assertEquals(3, violations.size());
    }

    @Test
    public void invalidImageTest() throws Exception {
        Image image = new Image("1213", "Ape", null);
        Set<ConstraintViolation<Image>> violations = validator.validate(image);
        assertEquals(3, violations.size());
    }

    @Test
    public void validImageTest() throws Exception {
        Image image = new Image("The gift within", "An Android program helping people to find there gift in life.", new File("images/image0.jpg)"));
        Set<ConstraintViolation<Image>> violations = validator.validate(image);
        assertTrue(violations.isEmpty());
    }

}

