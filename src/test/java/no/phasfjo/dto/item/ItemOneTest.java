package no.phasfjo.dto.item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by phasf on 27.01.2017.
 */
public class ItemOneTest {

    /*
    TODO
    TESTS WILL FAIL BECAUSE OF DATE ISSUES
     */

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
    public void nullValueItem2Test() throws Exception {
        ItemOne item = new ItemOne();
        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(10, violations.size());
    }

    @Test
    public void validItemTest() throws Exception {
        ItemOne item = new ItemOne();

        Date date = new Date();
        System.out.println(date);

        item.setDescription("A cd with Michael Jackson");
        item.setPrice(2f);                          //ok
        item.setInstantiate(date);
        item.setItemName("Dangerous");       //ok
        item.setItemType(ItemType.CD);      // ok


        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(0, violations.size());
    }

    @Test
    public void invalidItemTest() throws Exception {
        ItemOne item = new ItemOne();

        item.setItemName("Tes1");
        item.setItemType(ItemType.DUMMY_NOT_IN_LIST);
        item.setPrice(1f);
        item.setDescription("Ops!");
        item.setInstantiate(new Date("2/2/2038"));

        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(5, violations.size());
    }

    /*
        Test an invalid name entry!
    */
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidName() throws Exception {
        ItemOne item = new ItemOne();
        Date date = new Date();

        item.setItemName("abc");                             //ItemName violations is not 10-100 letters
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);

        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals("Invalid description must contain 5-50 letters", violations.iterator().next().getMessage());
        assertEquals("abc", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Description2.message}", violations.iterator().next().getMessageTemplate());
    }

    /*
    Test an invalid ItemType
     */
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidType() throws Exception {
        ItemOne item = new ItemOne();
        Date date = new Date();

        item.setItemName("Dangerous");
        item.setItemType(ItemType.DUMMY_NOT_IN_LIST);           //Violations data not in ItemType list
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);


        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(ItemType.DUMMY_NOT_IN_LIST, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid item type is not in the database", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.AllItemTypes.message}", violations.iterator().next().getMessageTemplate());
    }

    /*
    Test an invalid Price
    */
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPrice() throws Exception {
        ItemOne item = new ItemOne();
        Date date = new Date();

        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(1f);                                   //Violations price is below minimum 2f
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);

        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(1f, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid price must be more than 1", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Price.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDescription() throws Exception {
        ItemOne item = new ItemOne();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd");            //Violations description is not between 10-100 letters
        item.setInstantiate(date);

        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals("A cd", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDate() throws Exception {
        ItemOne item = new ItemOne();
        Date date = new Date("12/20/2019 20:00:00 GMT");
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);                      //Violations date

        Set<ConstraintViolation<ItemOne>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid date cannot be before todays date!", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.DateVal2.message}", violations.iterator().next().getMessageTemplate());
    }
}
