package no.phasfjo.infrastructure.account;

import no.phasfjo.dto.account.Account;
import no.phasfjo.dto.address.Address;
import no.phasfjo.dto.customer.Customer;
import no.phasfjo.dto.login.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class AccountIT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaAccountDao jpaAccountDao;
    private Account testAccount;
    private int generateID = 0;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaAccountDao = new JpaAccountDao(entityManager);

        testAccount = new Account();
    }

    @Test
    public void setupTest() throws Exception {

        testAccount.setCustomer(new Customer("Per", "karstain", "per@gmail.com", "90032122", new Date("08/05/1999"),  new Address(generateID(), "Innspurten 14", "Oslo", "Oslo", "0663", "Norway")));
        testAccount.setLogin(new Login("perKarsten", "per123213W#!"));

        entityManager.getTransaction().begin();
        jpaAccountDao.persist(testAccount);
        entityManager.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    /*
    Persist a new account with login and customer data.
     */
    @Test
    public void persistNewAccountTest() throws Exception {
        setupTest();

        Account account2 = new Account();

        account2.setCustomer(new Customer("Silje", "Siljesen", "silje@gmail.com", "40052551", new Date("08/05/1999"),  new Address(generateID(), "Gamle Fetveien 20", "Fetsund", "Aksershus", "0663", "Norway")));
        account2.setLogin(new Login("siljoRART", "silJE1234#!"));


        entityManager.getTransaction().begin();
        Account persistAccount = jpaAccountDao.persist(account2);
        entityManager.getTransaction().commit();

        assertNotSame(persistAccount.getCustomer().getAddress(), testAccount.getCustomer().getAddress());
        assertEquals(testAccount.getId(), 50);
        assertEquals(persistAccount.getId(), 51);
        assertNotNull(testAccount);
        assertNotSame(testAccount, persistAccount);
        assertTrue(persistAccount.getId() > 0);

    }

    /*
    Get all the account created by the init.script
     */
    @Test
    public void getAllAcountTest() throws Exception {
        setupTest();
        List<Account> list = jpaAccountDao.getAll();
        System.out.println("----ACCOUNTS IN TABLE -----");
        list.forEach(System.out::println);
        assertEquals(3, list.size());
    }

    /*
    GET accountlist + 2 new manually inputs
     */
    @Test
    public void getAllAcountsAfterTwoNewAccountPersistTest() throws Exception {

        //ACCOUNT_ONE_
        Account account = new Account();
        account.setCustomer(new Customer("Kaj", "Hansen", "kaj@yahoo.no", "90032131", new Date("04/02/1999 10:00:02"),  new Address(generateID(),"Drammensveien 507", "Høvik", "Akershus", "1363", "Norway")));
        account.setLogin(new Login("kajgooo", "kajRBangoo123#!"));

        //ACCOUNT_TWO_
        Account account2 = new Account();
        account2.setCustomer(new Customer("Mille", "Olsen", "Milo@yahoo.no", "40013022", new Date("02/02/1990 15:00:10"),new Address(generateID(),"8240 Buffalo RD", "Bergen", "New York", "14416", "USA")));
        account2.setLogin(new Login("Milllor", "MadeSpa2990123#!"));

        entityManager.getTransaction().begin();
        jpaAccountDao.persist(account);
        jpaAccountDao.persist(account2);
        entityManager.getTransaction().commit();

        List<Account> list = jpaAccountDao.getAll();
        System.out.println("--------New Account LIST ----");
        list.forEach(System.out::println);
        assertEquals(4, list.size());
    }

    /*
    Delete a account popularized via the init.script
     */
    @Test
    public void deleteAccountTest() throws Exception {
        Account account = jpaAccountDao.findById(1);
        entityManager.getTransaction().begin();
        boolean removed = jpaAccountDao.delete(account.getId());
        entityManager.getTransaction().commit();

        Account res = jpaAccountDao.findById(1);
        assertNull(res);
        assertNotEquals(jpaAccountDao.findById(1), "Account{id=1, customer=Customer{customerId=1, firstName='Kim', lastName='Pedersen', email='kim@yahoo.no', phoneNumber='90045870', birth=1980-11-05 00:00:00.0}, login= 'Login{Id=1, username='kimPedda', password='kimSimDimSum'}}");
        assertTrue(removed);
    }

    private int generateID(){
        generateID++;
        return generateID;
    }

    @Test
    public void persistNewAccount2Test() throws Exception {

        Account account = new Account();
        account.setCustomer(new Customer("Pauliano","Haffiord","pauliano@yahoo.no","90020300", new Date("02/02/1980"),new Address(generateID(),"Oslogate 200", "Oslo", "Oslo","0661","Norway")));
        account.setLogin(new Login("Paulky1980", "HPA900liano#@!"));

        entityManager.getTransaction().begin();
        Account newAccount = jpaAccountDao.persist(account);
        entityManager.getTransaction().commit();

        assertTrue(newAccount.getId() > 0);
    }
}
