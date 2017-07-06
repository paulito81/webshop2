package no.phasfjo.infrastructure.news.news;

import no.phasfjo.dto.news.News;
import no.phasfjo.dto.news.NewsId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by phasf on 27.01.2017.
 */
public class NewsIT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaNewsDao jpaNewsDao;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaNewsDao = new JpaNewsDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void persistANews() throws Exception {
        News news = new News(new NewsId("Liverpool - West Ham 2-2", "EN"), "A mind blowing football game were there just could be one winner!");
        entityManager.getTransaction().begin();
        News result = jpaNewsDao.persist(news);
        entityManager.getTransaction().commit();

        assertNotNull(result.toString().isEmpty());
        assertEquals("NewsId{title='Liverpool - West Ham 2-2', language='EN'}", result.getId().toString());
        assertEquals(result.getContent(), "A mind blowing football game were there just could be one winner!");
    }

    @Test
    public void shouldCreateTwoNewsAndGetAll() throws Exception {
        News news = new News(new NewsId("Liverpool - West Ham 2-2", "EN"), "A mind blowing football game were there just could be one winner!");
        News news2 = new News(new NewsId("Manchester - Birmingham 10-0", "EN"), "Crazy fooballers going wild!");

        entityManager.getTransaction().begin();
        News result = jpaNewsDao.persist(news);
        jpaNewsDao.persist(news2);
        entityManager.getTransaction().commit();

        assertEquals(news, result);
        assertEquals(2, jpaNewsDao.getAll().size());

    }
}
