package no.phasfjo.dto.comment;

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
public class CommentTest {

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
    public void nullValueCommentTest() throws Exception{
        Comment comment = new Comment();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(5, violations.size());
    }
    @Test
    public void invalidCommentTest()throws Exception{
        Comment comment = new Comment();
        comment.setContent("a");
        comment.setNickname("1");
        comment.setNote(0);
        comment.setPostedDate(new Date("2/2/2020"));

        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(3, violations.size());
    }

    @Test
    public void validCommentTest() throws Exception {
        Comment comment = new Comment();
        comment.setNote(1);
        comment.setPostedDate(new Date());
        comment.setNickname("Mariato82");
        comment.setContent("This site rocks!");

        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidNote() throws Exception {
        Comment comment = new Comment("Mariato81", "This site rocks", 0, new Date());
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());
        assertEquals(0, violations.iterator().next().getInvalidValue());
        assertEquals("must be greater than or equal to 1", violations.iterator().next().getMessage());
        assertEquals("{javax.validation.constraints.Min.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidNickName() throws Exception {
        Comment comment = new Comment("a", "This site rocks", 1, new Date());
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());
        assertEquals("a", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid userName must contain min 3 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.UserName.message}", violations.iterator().next().getMessageTemplate());
    }
    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidContent() throws Exception {
        Comment comment = new Comment("Mariato81", "11", 1, new Date());
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());
        assertEquals("11", violations.iterator().next().getInvalidValue());
        assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }
}
