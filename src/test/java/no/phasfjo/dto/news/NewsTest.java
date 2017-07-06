package no.phasfjo.dto.news;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by phasf on 27.01.2017.
 */
public class NewsTest {

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
    public void nullValueNewsTest() throws Exception {
        News news = new News();

        Set<ConstraintViolation<News>> violations = validator.validate(news);
        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void invalidNewsTest() throws Exception {
        News news = new News();
        NewsId newsId = new NewsId();
        news.setContent("1");
        newsId.setLanguage("ape");
        newsId.setTitle("121");
        Set<ConstraintViolation<News>> violations = validator.validate(news);
        Assert.assertEquals(2, violations.size());
    }

    @Test
    public void validNewsTest() throws Exception {
        NewsId newsId = new NewsId("Creativity novia", "English");
        News news = new News(newsId, "A new app is coming near you!");

        Set<ConstraintViolation<News>> violations = validator.validate(news);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldRaiseConstraintViolationsCausedByInvalidContent() throws Exception {
        NewsId newsId = new NewsId("New app is coming","English");
        News news = new News(newsId, "12");
        Set<ConstraintViolation<News>> violations = validator.validate(news);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("12", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("Invalid description must contain 10-200 letters", violations.iterator().next().getMessage());
        Assert.assertEquals("{no.phasfjo.dto.constraint.Description.message}", violations.iterator().next().getMessageTemplate());
    }

}
