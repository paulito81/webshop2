package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book;
import no.phasfjo.dto.item.ItemType;
import org.junit.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public class BookIT {
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaBookDao jpaBookDao;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaBookDao = new JpaBookDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }


    private String timeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.getTimeZone().getDisplayName();
    }

    @Test
    public void persistBookTest() {
        Date instance = new Date("12/19/2016 18:00:00 GMT");
        Book book = new Book();
        book.setTitle("Matilda");
        book.setPrice(20f);
        book.setDescription("About a girl");
        book.setIsbnNumber("1333213213211");
        book.setInitDate(instance);
        book.setIllustrations(false);
        book.setNumberOfPages(400);
        book.setVersion(1);
        book.setAuthor("Roald Dahl");
        book.setYear(1990);
        book.setQuantity(1);
        book.setItemType(ItemType.BOOK);

        entityManager.getTransaction().begin();
        Book result = jpaBookDao.persist(book);
        entityManager.getTransaction().commit();
        System.out.println("A new book was created:\t" + book);


        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals("Book{id=50, quantity=1, itemType=BOOK, title='Matilda', price=20.0, description='About a girl', isbnNumber='1333213213211', initDate=Mon Dec 19 13:00:00 EST 2016, illustrations=false, numberOfPages=400, version=1, author='Roald Dahl', year=1990}", result.toString());
                break;
            case "Central Eastern Time":
                assertEquals("Book{id=50, quantity=1, itemType=BOOK, title='Matilda', price=20.0, description='About a girl', isbnNumber='1333213213211', initDate=Mon Dec 19 19:00:00 CET 2016, illustrations=false, numberOfPages=400, version=1, author='Roald Dahl', year=1990}", result.toString());
                break;
            case "Central European Time":
                assertEquals("Book{id=50, quantity=1, itemType=BOOK, title='Matilda', price=20.0, description='About a girl', isbnNumber='1333213213211', initDate=Mon Dec 19 19:00:00 CET 2016, illustrations=false, numberOfPages=400, version=1, author='Roald Dahl', year=1990}", result.toString());
                break;
            case "Central Standard Time":
                assertEquals("Book{id=50, quantity=1, itemType=BOOK, title='Matilda', price=20.0, description='About a girl', isbnNumber='1333213213211', initDate=Mon Dec 19 12:00:00 CST 2016, illustrations=false, numberOfPages=400, version=1, author='Roald Dahl', year=1990}", result.toString());
                break;
            default:
                throw new IllegalArgumentException("Not a timezone");
        }

        Assert.assertTrue(result.getId() > 0);
    }

    @Test
    public void getAllBooksTest() {
        List<Book> bookList = jpaBookDao.getAllBooks();
        System.out.println("---BOOKS IN TABLE---");
        bookList.forEach(System.out::println);
        assertEquals(3, bookList.size());
    }

    //TODO FIX TEST

    @Test
    public void updateABookThatHasWrongEditorNameInputtedBySQL() throws Exception {
        Book book = jpaBookDao.findBookById(1);

        assertEquals("J.R.ROWLING", book.getAuthor());
        book.setAuthor("Joanne Kathleen Rowling");
        book.setNumberOfPages(309);
        book.setIsbnNumber("1389765387283");

        entityManager.getTransaction().begin();
        boolean updated = jpaBookDao.update(book);
        entityManager.getTransaction().commit();

        assertTrue(updated);
        assertEquals("309", jpaBookDao.findBookById(1).getNumberOfPages().toString());
        assertEquals("Joanne Kathleen Rowling", jpaBookDao.findBookById(1).getAuthor());

    }

    @Test
    public void updateAndCreateANewBook() throws Exception {
        Book newBook = new Book(1, ItemType.BOOK, "BFG", 199.0f, "BFG - big friendly gigant is about a nice gigant that put dreams into children's houses.", "9788205337961", new Date("06/30/2016 18:00:00"), true, 224, 1, "Roald Dahl", 1982);
        assertEquals(3, jpaBookDao.getAllBooks().size());

        entityManager.getTransaction().begin();
        jpaBookDao.persist(newBook);
        entityManager.getTransaction().commit();

        Book findById = jpaBookDao.findBookById(50);

        assertTrue(newBook.getId() == 50);
        assertEquals("BFG", findById.getTitle());
        assertNotSame(3, jpaBookDao.getAllBooks().size());

        newBook.setVersion(2);
        newBook.setDescription("BFG, it means a big friendly Gigant. He is awake every night and put dreams into children's houses.");

        entityManager.getTransaction().begin();
        boolean update = jpaBookDao.update(newBook);
        entityManager.getTransaction().commit();

        assertTrue(update);
        assertEquals("2", findById.getVersion().toString());
        assertEquals("BFG, it means a big friendly Gigant. He is awake every night and put dreams into children's houses.", findById.getDescription());
        assertSame(4, jpaBookDao.getAllBooks().size());

    }

    @Test
    public void deleteABookThatWasCreatedWithSQL() throws Exception {

        Book getBookOne = jpaBookDao.findBookById(1);

        assertEquals("Book{id=1, quantity=1, itemType=BOOK, title='Mio min Mio', price=100.0, description='Book about two brothers', isbnNumber='8-32138921322', initDate=2016-05-11 23:42:21.0, illustrations=false, numberOfPages=400, version=1, author='J.R.ROWLING', year=1979}", getBookOne.toString());

        entityManager.getTransaction().begin();
        boolean delete = jpaBookDao.delete(getBookOne.getId());
        entityManager.getTransaction().commit();

        assertTrue(delete);
        assertNull(jpaBookDao.findBookById(1));
        assertEquals(null, jpaBookDao.findBookById(1));
        assertNotEquals("Book{id=1, quantity=1, itemType=BOOK, title='Harry Potter and the seven wise stones', price=199.0, description='Harry Potter and the seven wise stone is a wonderful illustrated edition.', isbnNumber='9788202459772', initDate=2015-10-10 10:00:00.0, illustrations=true, numberOfPages=350, version=1, author='Rowling J.K', year=2015}", jpaBookDao.findBookById(1));
    }

    /*
   CRITERIA BUILDER GET ALL BOOKS TEST
    */
    @Test
    public void getAllBooksWithCriteriaBuilder() throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = criteriaQuery.from(Book.class);

        CriteriaQuery<Book> select = criteriaQuery.select(from);
        TypedQuery<Book> query = entityManager.createQuery(select);
        List<Book> allItems = query.getResultList();

        allItems.forEach(System.out::println);

        assertNotNull(allItems);
        assertEquals(3, allItems.size());
    }


    //TODO FIX!

    @Test
    @Ignore
    public void shouldCallANamedStoredProcedureQuery() throws Exception {

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("archiveOldBooks");

        query.setParameter("archiveDate", new Date());
        query.setParameter("maxBookArchived", 1000);
        query.execute();
    }

    @Test
    @Ignore
    public void shouldCallAStoredProcedureQuery() throws Exception {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_archive_old_books");
        query.registerStoredProcedureParameter("archiveDate", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("maxBookArchived", Integer.class, ParameterMode.IN);

        query.setParameter("archiveDate", new Date());
        query.setParameter("maxBookArchived", 1000);
        query.execute();
    }

    @Test
    public void shouldCheckThatABookisCacheableEqualseFalse() throws Exception {

        Book newBook = new Book(1, ItemType.BOOK, "BFG", 199.0f, "BFG - big friendly gigant is about a nice gigant that put dreams into children's houses.", "9788205337961", new Date("06/30/2016 18:00:00"), true, 224, 1, "Roald Dahl", 1982);

        entityManager.getTransaction().begin();
        jpaBookDao.persist(newBook);
        entityManager.getTransaction().commit();

        assertNotNull(newBook.getId());
        Cache cache = factory.getCache();

        assertFalse(cache.contains(Book.class, newBook.getId()));
    }

}
