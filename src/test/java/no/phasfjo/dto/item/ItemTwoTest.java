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
public class ItemTwoTest {

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
        ItemTwo item = new ItemTwo();
        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(21, violations.size());
    }

    @Test
    public void validItemTest() throws Exception {
        ItemTwo item = new ItemTwo();

        Date date = new Date();
        System.out.println(date);

        item.setDescription("A cd with Michael Jackson");
        item.setColor("blue");
        item.setHeight(1d);
        item.setWeight(1d);
        item.setWidth(1d);
        item.setManufacturernumber("B01HH5ZFPA");  //ok
        item.setPrice(2f);                          //ok
        item.setInstantiate(date);
        item.setItemName("Dangerous");       //ok
        item.setItemType(ItemType.CD);      // ok
        item.setQuantity(1);


        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(0, violations.size());
    }

    @Test
    public void invalidItemTest() throws Exception {
        ItemTwo item = new ItemTwo();

        item.setItemName("Tes1");
        item.setItemType(ItemType.DUMMY_NOT_IN_LIST);
        item.setPrice(1f);
        item.setDescription("Ops!");
        item.setInstantiate(new Date("2/2/2029"));
        item.setColor("X");
        item.setHeight(0d);
        item.setWidth(0d);
        item.setWeight(0d);
        item.setManufacturernumber("ape1234");
        item.setQuantity(0);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(11, violations.size());
    }

    /*
        Test an invalid name entry!
    */
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidName() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();

        item.setItemName("abc");                             //ItemName violations is not 10-100 letters
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
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
        ItemTwo item = new ItemTwo();
        Date date = new Date();

        item.setItemName("Dangerous");
        item.setItemType(ItemType.DUMMY_NOT_IN_LIST);           //Violations data not in ItemType list
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
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
        ItemTwo item = new ItemTwo();
        Date date = new Date();

        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(1f);                                   //Violations price is below minimum 2f
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(1f, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid price must be more than 1", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Price.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDescription() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd");            //Violations description is not between 10-100 letters
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals("A cd", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDate() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date("12/20/2019 20:00:00 GMT");
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);                      //Violations description is not between 5-50 letters
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid date cannot be before todays date!", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.DateVal2.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidColor() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("bl");                                  //Violations color is not between 3-30 letters
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals("bl", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid item must contain min 3 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Item.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidHeight() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(-1d);                                 //Violations height must be min 1
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(-1d, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid size must be positive size", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.MinSize.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidWidth() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(-1d);                                  //Violations width must be min 1
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(-1d, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid size must be positive size", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.MinSize.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidWeight() {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(-1d);                                 //Violations weight must be min 1
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(-1d, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid size must be positive size", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.MinSize.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidManufacture() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01H");           // Violations manufacture must contain 10-200 letters
        item.setQuantity(1);

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals("B01H", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQuantity() throws Exception {
        ItemTwo item = new ItemTwo();
        Date date = new Date();
        item.setItemName("Dangerous");
        item.setItemType(ItemType.CD);
        item.setPrice(2f);
        item.setDescription("A cd with Michael Jackson");
        item.setInstantiate(date);
        item.setColor("blue");
        item.setHeight(1d);
        item.setWidth(1d);
        item.setWeight(1d);
        item.setManufacturernumber("B01HH5ZFPA");
        item.setQuantity(0);                        // Violations quantity must minimum be 1

        Set<ConstraintViolation<ItemTwo>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }
}
