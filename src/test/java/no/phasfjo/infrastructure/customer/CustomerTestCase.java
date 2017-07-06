package no.phasfjo.infrastructure.customer;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by phasf on 13.11.2016.
 */
public class CustomerTestCase {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaCustomerDao jpaCustomerDao;

    public JpaCustomerDao initJpaCustomerDao() {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaCustomerDao = new JpaCustomerDao(entityManager);

        return jpaCustomerDao;
    }

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaCustomerDao = new JpaCustomerDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {

        entityManager.close();
        factory.close();
    }


    public JpaCustomerDao getJpaCustomerDao() {
        return jpaCustomerDao;
    }

    public void setJpaCustomerDao(JpaCustomerDao jpaCustomerDao) {
        this.jpaCustomerDao = jpaCustomerDao;
    }
}