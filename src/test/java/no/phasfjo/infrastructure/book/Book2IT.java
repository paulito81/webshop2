package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
public class Book2IT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaBook2Dao jpaBookDao;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaBookDao = new JpaBook2Dao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void persistBook2Test() {
        Date instance = new Date();
        Book2 book = new Book2();
        book.setTitle("Matilda");
        book.setPrice(20f);
        book.setDescription("About a girl");
        book.setIsbnNumber("1333213213211");
        book.setInitDate(instance);
        book.setIllustrations(false);
        book.setNumberOfPages(400);
        book.setVersion(1);
        book.setAuthor("Roald Dahl");
        book.setEditor("Cappelen");
        book.setYear(1990);

        entityManager.getTransaction().begin();
        Book2 result = jpaBookDao.persist(book);
        entityManager.getTransaction().commit();
        System.out.println("A new book was created:\t" + book);
        Assert.assertTrue(result.getId() > 0);
    }

    @Test
    public void getAllBooksTest() {
        List<Book2> bookList = jpaBookDao.getAllBooks();
        System.out.println("---BOOKS IN TABLE---");
        bookList.forEach(System.out::println);
        assertEquals(2, bookList.size());
    }

    @Test
    public void updateABook2ThatHasWrongIsbnInputtedBySQL() throws Exception {
        Book2 book = jpaBookDao.findBookById(1);

        book.setIllustrations(true);
        book.setIsbnNumber("8321389213223");
        book.setEditor("Anne Cath Vestby");
        book.setYear(1992);
        book.setVersion(2);

        entityManager.getTransaction().begin();
        boolean updated = jpaBookDao.update(book);
        entityManager.getTransaction().commit();

        assertTrue(updated);

        assertNotSame(book.toString(), jpaBookDao.findBookById(1).toString());
        assertEquals("2", jpaBookDao.findBookById(book.getId()).getVersion().toString());
        assertEquals(true, jpaBookDao.findBookById(1).getIllustrations());
        assertNotSame("8-32138921322", jpaBookDao.findBookById(1).getIsbnNumber());
        assertSame("8321389213223", jpaBookDao.findBookById(1).getIsbnNumber());
        assertTrue(jpaBookDao.findBookById(1).getYear() == 1992);

    }

    @Test
    public void createAndUpdateABook2() throws Exception {

        Book2 book = new Book2();
        book.setTitle("Aliens Are coming");
        book.setPrice(100f);
        book.setDescription("About the last of us surviving the aliens invation");
        book.setIsbnNumber("1367895353535");
        book.setInitDate(new Date("12/14/2016 20:00:00 GMT"));
        book.setIllustrations(true);
        book.setNumberOfPages(358);
        book.setVersion(1);
        book.setEditor("20th Century Fox");
        book.setAuthor("Ridley Scott");
        book.setYear(1979);

        entityManager.getTransaction().begin();
        Book2 res = jpaBookDao.persist(book);
        entityManager.getTransaction().commit();

        System.out.println(res.toString());
        assertTrue(res.getId() == 50);
        assertEquals("Book2{id=50, version=1, illustrations=true, numberOfPages=358, title='Aliens Are coming', price=100.0, description='About the last of us surviving the aliens invation', isbnNumber='1367895353535', initDate=Wed Dec 14 21:00:00 CET 2016, author='Ridley Scott', editor='20th Century Fox', year=1979}", res.toString());

        res.setVersion(2);
        res.setPrice(105f);
        res.setNumberOfPages(390);

        entityManager.getTransaction().begin();
        boolean update = jpaBookDao.update(res);
        entityManager.getTransaction().commit();

        /*
        FIX
         */
        assertTrue(update);
        System.out.println(jpaBookDao.findBookById(50).toString());
        assertEquals("Book2{id=50, version=2, illustrations=true, numberOfPages=390, title='Aliens Are coming', price=105.0, description='About the last of us surviving the aliens invation', isbnNumber='1367895353535', initDate=Wed Dec 14 21:00:00 CET 2016, author='Ridley Scott', editor='20th Century Fox', year=1979}", jpaBookDao.findBookById(50).toString());

        res.setDescription("About the last of us surviving the alien invasion");
        res.setTitle("Aliens are coming!");
        res.setVersion(3);


        entityManager.getTransaction().begin();
        boolean update2 = jpaBookDao.update(res);
        entityManager.getTransaction().commit();

        System.out.println(res);

        assertTrue(update2);
        assertEquals("Book2{id=50, version=3, illustrations=true, numberOfPages=390, title='Aliens are coming!', price=105.0, description='About the last of us surviving the alien invasion', isbnNumber='1367895353535', initDate=Wed Dec 14 21:00:00 CET 2016, author='Ridley Scott', editor='20th Century Fox', year=1979}", jpaBookDao.findBookById(50).toString());
    }

    @Test
    public void deleteABookThatWasCreatedWithSQL() throws Exception {
        Book2 book = jpaBookDao.findBookById(1);

        assertEquals(1, book.getId());

        entityManager.getTransaction().begin();
        boolean deleted = jpaBookDao.delete(1);
        entityManager.getTransaction().commit();

        assertTrue(deleted);
        assertNull(jpaBookDao.findBookById(1));
    }

    /*
   CRITERIA BUILDER GET ALL BOOKS TEST
    */
    @Test
    public void getAllBooksWithCriteriaBuilder() throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book2> criteriaQuery = criteriaBuilder.createQuery(Book2.class);
        Root<Book2> from = criteriaQuery.from(Book2.class);

        CriteriaQuery<Book2> select = criteriaQuery.select(from);
        TypedQuery<Book2> query = entityManager.createQuery(select);
        List<Book2> allItems = query.getResultList();

        allItems.forEach(System.out::println);

        assertNotNull(allItems);
        assertEquals(2, allItems.size());
    }
    /*
    CRITERIA BUILDER PERSIST A NEW BOOK DOES NOT WORK?
     */
/*

    @Test
    public void persistANewBookWitCriteriaBuilder() throws Exception {
        String update = "INSERT INTO BOOK2(id, title, price, description, isbnNumber, initDate, illustrations, numberOfPages, version, author, editor, year) SELECT (3, 'Hoda fra Pjott', 129.0, 'En bok om en gutt som finner et flyvende teppe', '9744303522933', '2009-02-12 10:00:00', 'true', 145, 1, 'Kirkegaard', 'Gyldendal', 1995) " ;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book2> criteriaQuery = criteriaBuilder.createQuery(Book2.class);
        Root<Book2> from = criteriaQuery.from(Book2.class);

        TypedQuery<Book2> query = (TypedQuery<Book2>) entityManager.createQuery(update);
        List<Book2> books =  query.getResultList();
        books.forEach(System.out::println);

    }
*/
}
