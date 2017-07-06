package no.phasfjo.infrastructure.login;

import no.phasfjo.dto.login.Login;
import org.junit.*;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class LoginIT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaLoginDao jpaLoginDao;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaLoginDao = new JpaLoginDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void persistANewLogin() throws Exception {
        entityManager.getTransaction().begin();
        Login login = new Login();
        login.setUsername("perHansen1980");
        login.setPassword("HansaErBestPaaFest123!@#");
        Login result = jpaLoginDao.persist(login);
        entityManager.getTransaction().commit();
        System.out.println(result);
        assertNotEquals("Login{Id=0, username='perHansen1980', password='HansaErBestPaaFest123!@#'}", result.toString());
        assertEquals("Login{Id=50, username='perHansen1980', password='HansaErBestPaaFest123!@#'}", result.toString());
    }

    @Test
    public void updateLogin() throws Exception {
        entityManager.getTransaction().begin();
        Login login1 = entityManager.find(Login.class, 1);
        login1.setPassword("aapekatt123BFG!£$");
        jpaLoginDao.update(login1);
        entityManager.getTransaction().commit();

        boolean updated = jpaLoginDao.update(login1);
        Assert.assertTrue(updated);
        Assert.assertEquals(login1.getPassword(), "aapekatt123BFG!£$");
    }

    /*
    //TODO java.lang.AssertionError = value is False?
     */

    @Test
    @Ignore
    public void shouldCheckThatCustomerIsCacheableEqualsTrue() throws Exception {

        Login login = new Login("perHansen1980", "HansaErBestPaaFest123!@#");


        entityManager.getTransaction().begin();
        jpaLoginDao.persist(login);
        entityManager.getTransaction().commit();

        assertNotNull(login.getId());
        Cache cache = factory.getCache();

        assertTrue(cache.contains(Login.class, login.getId()));

        cache.evict(Login.class);

        //Cache is cleared and no longer cached.
        assertFalse(cache.contains(Login.class, login.getId()));
    }
}
