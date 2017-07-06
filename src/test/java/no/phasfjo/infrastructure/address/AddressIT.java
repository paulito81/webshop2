package no.phasfjo.infrastructure.address;

import no.phasfjo.dto.address.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class AddressIT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaAddressDao jpaAddressDao;
    private Address address;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaAddressDao = new JpaAddressDao(entityManager);
        address = new Address();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    /*
    Test persist a new address
     */
    @Test
    public void persistAddressTest() throws Exception {

        address = new Address("Innspurten 14", "Oslo", "Oslo", "0663", "Norway");
        entityManager.getTransaction().begin();
        Address result = jpaAddressDao.persist(address);
        entityManager.getTransaction().commit();
        assertTrue(result.getId() > 0);
        assertEquals(result.toString(), "Address{id=50, street='Innspurten 14', city='Oslo', state='Oslo', zipcode='0663', country='Norway'}");
    }

    /*
    Test get all addresses by list.
     */
    @Test
    public void getAllAddressTest() throws Exception {
        List<Address> addressList = jpaAddressDao.getAll();
        System.out.println("-----ADDRESS IN TABLE -----");
        addressList.forEach(System.out::println);
        assertEquals(2, addressList.size());
    }

    /*
    Test update an address
     */
    @Test
    public void updateAddressTest() throws Exception {
        Address address = jpaAddressDao.findById(1);
        entityManager.detach(address);

        address.setStreet("testStreet 200");
        address.setCity("TestCity");
        address.setState("TestState");
        address.setCountry("TestCountry");
        address.setZipcode("11111");

        entityManager.getTransaction().begin();
        boolean updated = jpaAddressDao.update(address);
        entityManager.getTransaction().commit();

        Address result = jpaAddressDao.findById(1);
        assertTrue(updated);
        assertEquals(address.toString(), result.toString());
        assertNotEquals(address.getStreet(), "189 2 Ave'");
    }
}
