package no.phasfjo.infrastructure.generator;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static no.phasfjo.infrastructure.generator.Constants.PERSISTENCE_UNIT_NAME;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish 3
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 */
public class AbstractPersistenceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    protected EntityManager entityManager;
    protected EntityTransaction transaction;

    // ======================================
    // =             Lifecycle Methods      =
    // ======================================

    @Before
    public void initEntityManager() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @After
    public void closeEntityManager() throws SQLException {
        if (entityManager != null && entityManagerFactory != null) {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
