package no.phasfjo.infrastructure.creditcard.creditcard2;

import no.phasfjo.dto.creditcard.CreditCard2;
import no.phasfjo.dto.creditcard.CreditcardType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by phasf on 27.01.2017.
 */
public class CreditCard2IT {
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaCreditCard2Dao jpaCreditCardDao;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaCreditCardDao = new JpaCreditCard2Dao(entityManager);

    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void peristNewCreditCardTest() throws Exception {

        CreditCard2 creditCard2 = new CreditCard2("343456789065436", new Date("12/12/2018 20:00:00 GMT"), "333", CreditcardType.AMERICAN_EXPRESS);
        entityManager.getTransaction().begin();
        CreditCard2 result = jpaCreditCardDao.persist(creditCard2);
        entityManager.getTransaction().commit();

        assertTrue(result.getId() > 0);
        assertEquals(result.getCardNumber(), "343456789065436");
    }

    @Test
    public void createAndDeleteACreditCardTest() throws Exception {
        //CREATE
        CreditCard2 creditCard2 = new CreditCard2("343456789065436", new Date("12/12/2018 20:00:00 GMT"), "333", CreditcardType.AMERICAN_EXPRESS);
        entityManager.getTransaction().begin();
        CreditCard2 result = jpaCreditCardDao.persist(creditCard2);
        entityManager.getTransaction().commit();

        //DELETE
        entityManager.getTransaction().begin();
        boolean deleted = jpaCreditCardDao.delete(result.getId());
        entityManager.getTransaction().commit();

        assertTrue(deleted);
        assertEquals(jpaCreditCardDao.findById(result.getId()), null);
        assertNotSame(result, jpaCreditCardDao.findById(50));
    }

    @Test
    public void createTenNewCreditCardGetAllTest() throws Exception {
        //CREATE
        CreditCard2 creditCard = new CreditCard2("343456789065436", new Date("12/12/2018 20:00:00 GMT"), "333", CreditcardType.AMERICAN_EXPRESS);
        CreditCard2 creditCard2 = new CreditCard2("6541890929394123", new Date("12/12/2017 20:00:00 GMT"), "444", CreditcardType.BCG_GLOBAL);
        CreditCard2 creditCard3 = new CreditCard2("38998374783932", new Date("12/12/2019 10:00:00 GMT"), "123", CreditcardType.CARTE_BLANCHE);
        CreditCard2 creditCard4 = new CreditCard2("30534958493821", new Date("12/9/2020 11:00:00 GMT"), "221", CreditcardType.DINERS_CLUB);
        CreditCard2 creditCard5 = new CreditCard2("6521394792349124", new Date("12/11/2017 21:00:00 GMT"), "984", CreditcardType.DISCOVERY);
        CreditCard2 creditCard6 = new CreditCard2("213183749245632", new Date("12/10/2017 22:00:00 GMT"), "032", CreditcardType.JCB);
        CreditCard2 creditCard7 = new CreditCard2("502045394045", new Date("12/12/2021 00:10:00 GMT"), "763", CreditcardType.MAESTRO);
        CreditCard2 creditCard8 = new CreditCard2("5188743672454325", new Date("12/12/2027 02:00:00 GMT"), "255", CreditcardType.VISA_MASTERCARD);
        CreditCard2 creditCard9 = new CreditCard2("4989432785433", new Date("12/12/2018 09:00:00 GMT"), "174", CreditcardType.VISA);
        CreditCard2 creditCard10 = new CreditCard2("6289482394753255", new Date("12/12/2017 10:10:00 GMT"), "671", CreditcardType.UNION_PAY);

        entityManager.getTransaction().begin();
        CreditCard2 res1 = jpaCreditCardDao.persist(creditCard);
        CreditCard2 res2 = jpaCreditCardDao.persist(creditCard2);
        jpaCreditCardDao.persist(creditCard3);
        jpaCreditCardDao.persist(creditCard4);
        jpaCreditCardDao.persist(creditCard5);
        jpaCreditCardDao.persist(creditCard6);
        jpaCreditCardDao.persist(creditCard7);
        jpaCreditCardDao.persist(creditCard8);
        jpaCreditCardDao.persist(creditCard9);
        CreditCard2 res10 = jpaCreditCardDao.persist(creditCard10);

        entityManager.getTransaction().commit();

        assertNotNull(res1);
        assertNotNull(res2);
        assertEquals(10, jpaCreditCardDao.getAll().size());
        assertNotSame(res1, res2);
        assertTrue(res1.getId() == 50);
        assertTrue(res10.getId() == 59);
    }

}
