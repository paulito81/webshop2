package no.phasfjo.dto.book;

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
 * Created by paulhasfjord on 15.12.2016.
 */
public class Book2Test {


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
    public void nullValueBook2Test() throws Exception {
        Book2 book = new Book2();
        Set<ConstraintViolation<Book2>> violations = validator.validate(book);
        assertEquals(19, violations.size());
    }
    //TODO Create a test that correct Float price to 0, minus as a violation.

    @Test
    public void invalidBook2Test() throws Exception {
        Book2 book = new Book2();
        Date date = new Date("8/10/2200 20:00:10 GMT");
        book.setInitDate(date);                   //{InitDate}   violates- future date not allowed
        book.setIsbnNumber("12-34576789555");        //{Number] violates- just 13 numbers
        book.setDescription(null);               // Desc] violates- null value not allowed
        book.setTitle("a");                      // [Title] violates- less than required 3 letters title
        book.setPrice(null);                     // [Price] violates- value is null
        book.setIllustrations(null);            // [Illustration] must have a valid value
        book.setNumberOfPages(4);               // [Pagenum] must be between 10- 10 000 pages
        book.setEditor(null);                   // [Editor] cannot be empty or null
        book.setYear(1500);                     // [YEAR] must be after 1529
        book.setVersion(0);                     // [VER] must be + 1
        book.setAuthor("");                     // [AUTHOR] must have a value

        Set<ConstraintViolation<Book2>> violations = validator.validate(book);
        assertEquals(14, violations.size());
        System.out.println(book.getIsbnNumber().length());
    }

    @Test
    public void validBook2Test() throws Exception {
        Date date = new Date();
        Book2 book = new Book2();

        book.setTitle("Mr Pablo");                  // TITLE min 10 letters
        book.setPrice(20f);                         // PRICE min 2.0
        book.setInitDate(date);                     // DATE not in the future
        book.setIsbnNumber("1322321356722");        // ISBN 13 digits!
        book.setDescription("A book about Pablo Escobar");      // DESC min 10 letters
        book.setIllustrations(true);                        // ILLUSTRATION true/false
        book.setNumberOfPages(400);                         // PAGES min 10
        book.setAuthor("Pablo Francisco");                  // AUTHOR min 10 letters
        book.setEditor("20th Fox Century");                 // Editor min 10 letters?
        book.setYear(1999);                                 // YEAR min 1519
        book.setVersion(1);                                 // Version min 1
        Set<ConstraintViolation<Book2>> violations = validator.validate(book);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidTitleIsOneLetter() throws Exception {
        Book2 book0 = new Book2("V", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"Arne Berggren", "Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book0);
        assertEquals(1, violations.size());
        assertEquals("V", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid title must contain 3-50 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Title.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolatonsCausedByInvalidPriceIsNull() throws Exception {
        Book2 book1 = new Book2("Vanessa", 0f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"Arne Berggren", "Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book1);
        assertEquals(1, violations.size());
        assertEquals("Invalid price must be more than 1", violations.iterator().next().getMessage());
        assertEquals(0f, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Price.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDescriptionMoreThan200Letters() throws Exception {
        Book2 book2 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente json ipsum dipsum Arne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum", "1321224566772", new Date("2/2/1990"), false, 400,1,"Arne Berggren","Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book2);
        assertEquals(1, violations.size());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("En bok om en ungdomsjente json ipsum dipsum Arne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsumArne Berggren json ipsum dipsum", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidIsbnHas12digits() throws Exception {
        Book2 book3 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "13-122456677", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"Arne Berggren", "Gyldendal" ,1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book3);
        assertEquals(1, violations.size());
        assertEquals("Invalid isbn must contain 13 digits", violations.iterator().next().getMessage());
        assertEquals("13-122456677", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Isbn.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidDateIsInTheFuture() throws Exception {
        Date date = new Date("2/2/2200 20:00:00 GMT");
        Book2 book4 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", date, false, 400,1,"Arne Berggren", "Gyldendal" ,1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book4);
        assertEquals(1, violations.size());
        assertEquals("Invalid date cannot be in the future", violations.iterator().next().getMessage());
        assertEquals(date, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.InitDate.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidIllustration() throws Exception {
        Book2 book5 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), null, 400,1,"Arne Berggren", "Gyldedal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book5);
        assertEquals(1, violations.size());
        assertEquals("Invalid illustration is set to null", violations.iterator().next().getMessage());
        assertEquals(null, violations.iterator().next().getInvalidValue());
        assertEquals("Invalid illustration is set to null", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidPageNumberIsToLow() throws Exception {
        Book2 book6 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 5,1,"Arne Berggren","Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book6);
        assertEquals(1, violations.size());
        assertEquals("Invalid page number must be between 10-10000 pages", violations.iterator().next().getMessage());
        assertEquals(5, violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.PageNumber.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidVersionNumber() throws Exception {
        Book2 book7 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,0,"Arne Berggren","Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book7);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidAuthor() throws Exception {
        Book2 book8 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"","Gyldendal",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book8);
        assertEquals(1, violations.size());
        assertEquals("Invalid author name must contain 10 to 30 letters", violations.iterator().next().getMessage());
        assertEquals("", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Author.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidEditor() throws Exception {
        Book2 book9 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"Arne Berggren","",1999);
        Set<ConstraintViolation<Book2>> violations = validator.validate(book9);
        assertEquals(1, violations.size());
        assertEquals("Invalid editor name must contain 10 to 30 letters", violations.iterator().next().getMessage());
        assertEquals("", violations.iterator().next().getInvalidValue());
        assertEquals("{no.phasfjo.dto.constraint.Editor.message}", violations.iterator().next().getMessageTemplate());
    }


    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidYearIsToEarly() throws Exception {
        Book2 book9 = new Book2("Vanessa", 30f, "En bok om en ungdomsjente", "1321224566772", new Date("2/2/1990 20:00:00 GMT"), false, 400,1,"Arne Berggren","Gyldendal",1500);
        Set<ConstraintViolation<Book2>> violations =validator.validate(book9);
        assertEquals(1, violations.size());
        assertEquals("must be greater than or equal to 1519", violations.iterator().next().getMessage());
        assertEquals(1500, violations.iterator().next().getInvalidValue());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }




}
