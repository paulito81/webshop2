package no.phasfjo.dto.book;

import no.phasfjo.dto.item.ItemType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class BookTest {
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
    public void nullValueBook3Test() throws Exception {
        Book book = new Book();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(19, violations.size());
    }

    @Test
    public void invalidBook3Test() throws Exception {
        Book book = new Book();
        book.setItemType(ItemType.DUMMY_NOT_IN_LIST);                // |Item]       violates- not in list
        book.setTitle("a");                                          // [Title]      violates- less than required 3 letters title
        book.setPrice(0f);                                            // [Price]      violates- value  min 1
        book.setDescription("a");                                    // [Desc]       violates- min 2 letters
        book.setInitDate(new Date("8/10/2200 20:00:10 GMT"));     // [InitDate]   violates- future date not allowed
        book.setIsbnNumber("12-34576789555");                        // [Number]     violates- just 13 numbers
        book.setIllustrations(null);                                 // [Illustration] must be set to true or false
        book.setNumberOfPages(4);                                    // [Pagenum]      must be between 10- 10 000 pages
        book.setVersion(0);                                          // [version]     Must be min 1.
        book.setAuthor("ape");                                       // |Editor]      Must have at least 7 letters
        book.setYear(1500);                                          // [Year]        Must be after 1519
        book.setQuantity(0);

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(12, violations.size());
    }

    @Test
    public void validBook3Test() throws Exception {

        Book book = new Book();

        Date date = new Date();
        book.setItemType(ItemType.BOOK);
        book.setTitle("Mr Pablo");
        book.setPrice(20f);
        book.setDescription("A book about Pablo Escobar");
        book.setInitDate(date);
        book.setIsbnNumber("1345678956789");
        book.setIllustrations(true);
        book.setNumberOfPages(400);
        book.setVersion(1);
        book.setAuthor("Pablo Francisco");
        book.setYear(1999);
        book.setQuantity(1);

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidQuantity() throws Exception {

        Book book0 = new Book(1, ItemType.DUMMY_NOT_IN_LIST, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book0);
        assertEquals(1, violations.size());
        assertEquals(ItemType.DUMMY_NOT_IN_LIST, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid item type is not in the database", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.AllItemTypes.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidItemType() throws Exception {
        Book book000 = new Book(0, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book000);
        assertEquals(1, violations.size());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());

    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidTitleIsOneLetter() throws Exception {

        Book book00 = new Book(1, ItemType.BOOK, "V", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book00);
        assertEquals(1, violations.size());
        assertEquals("V", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid title must contain 3-50 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Title.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolatonsCausedByInvalidPriceIsNull() throws Exception {
        Book book1 = new Book(1, ItemType.BOOK, "Vanessa", 0f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book1);
        assertEquals(1, violations.size());
        assertEquals("Invalid price must be more than 1", violations.iterator().next().getMessage());
        assertEquals(0f, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Price.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDescriptionMoreThan200Letters() throws Exception {
        Book book2 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente json ipsum dipsum Arne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum", "1321224566772", new Date("2/2/1990"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book2);
        assertEquals(1, violations.size());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("En bok om en ungdomsjente json ipsum dipsum Arne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidIsbnHas12digits() throws Exception {
        Book book3 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "13-122456677", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book3);
        assertEquals(1, violations.size());
        assertEquals("Invalid isbn must contain 13 digits", violations.iterator().next().getMessage());
        assertEquals("13-122456677", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Isbn.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDateIsInTheFuture() throws Exception {
        Date date = new Date("2/2/2200 20:00:00 GMT");
        Book book4 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", date, false, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book4);
        assertEquals(1, violations.size());
        assertEquals("Invalid date cannot be in the future", violations.iterator().next().getMessage());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.InitDate.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidIllustration() throws Exception {
        Book book5 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), null, 400, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book5);
        assertEquals(1, violations.size());
        assertEquals("Invalid illustration is set to null", violations.iterator().next().getMessage());
        assertEquals(null, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid illustration is set to null", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPageNumberIsToLow() throws Exception {
        Book book6 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 5, 1, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book6);
        assertEquals(1, violations.size());
        assertEquals("Invalid page number must be between 10-10000 pages", violations.iterator().next().getMessage());
        assertEquals(5, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.PageNumber.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidVersionNumber() throws Exception {
        Book book7 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 0, "Arne Berggren", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book7);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidEditor() throws Exception {
        Book book8 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "", 1999);
        Set<ConstraintViolation<Book>> violations = validator.validate(book8);
        assertEquals(1, violations.size());
        assertEquals("Invalid author name must contain 10 to 30 letters", violations.iterator().next().getMessage());
        assertEquals("", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Author.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidYearIsToEarly() throws Exception {
        Book book9 = new Book(1, ItemType.BOOK, "Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400, 1, "Arne Berggren", 1500);
        Set<ConstraintViolation<Book>> violations = validator.validate(book9);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 1519", violations.iterator().next().getMessage());
        assertEquals(1500, violations.iterator().next().getInvalidValue());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }
}
