package no.phasfjo.dto.news;

import no.phasfjo.dto.comment.Comment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by phasf on 27.01.2017.
 */
public class News2Test {
    private Validator validator;
    public List<Comment> list;


    @Before
    public void setup() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        list = new ArrayList<>();

    }

    @After
    public void teardown() throws Exception {
        validator = null;
    }

    @Test
    public void nullValueNews2Test() throws Exception {
        News2 news = new News2();

        Set<ConstraintViolation<News2>> violations = validator.validate(news);
        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void invalidNews2Test() throws Exception {
        News2 news = new News2("1", null);

        Set<ConstraintViolation<News2>> violations = validator.validate(news);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void validNews2Test() throws Exception {
        Comment comment = new Comment("parkinson812", "An app in creation", 1, new Date());
        Comment comment2 = new Comment("deadbeat", "what kind of app?", 2, new Date());
        list.add(comment);
        list.add(comment2);

        NewsId newsId = new NewsId("Creativity novia", "English");
        News2 news = new News2("A new app is coming near you!", list);

        Set<ConstraintViolation<News2>> violations = validator.validate(news);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidContent() throws Exception {
        Comment comment = new Comment("parkinson812", "An app in creation", 1, new Date());
        Comment comment2 = new Comment("deadbeat", "what kind of app?", 2, new Date());
        list.add(comment);
        list.add(comment2);

        News2 news = new News2("K2", list);
        Set<ConstraintViolation<News2>> violations = validator.validate(news);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("K2", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        Assert.assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }
}
