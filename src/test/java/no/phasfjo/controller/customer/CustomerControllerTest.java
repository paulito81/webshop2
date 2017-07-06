package no.phasfjo.controller.customer;

import no.phasfjo.infrastructure.address.JpaAddressDao;
import no.phasfjo.infrastructure.customer.JpaCustomerDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class CustomerControllerTest {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaCustomerDao jpaCustomerDao;
    private JpaAddressDao jpaAddressDao;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaCustomerDao = new JpaCustomerDao(entityManager);
        jpaAddressDao = new JpaAddressDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {

        entityManager.close();
        factory.close();
    }

    @Test
    public void customerControllerPersistTest() throws Exception {

    }


}
