package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;
import no.phasfjo.dto.item.ItemType;
import org.junit.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by paulhasfjord on 19.12.2016.
 */
public class Book3IT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaBook3Dao jpaBookDao;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaBookDao = new JpaBook3Dao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    @Test
    public void persistBook3Test() {
        Date instance = new Date("12/19/2016 18:00:00 GMT");
        Book3 book = new Book3();
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
        book.setQuantity(1);
        book.setItemType(ItemType.BOOK);

        entityManager.getTransaction().begin();
        Book3 result = jpaBookDao.persist(book);
        entityManager.getTransaction().commit();
        System.out.println("A new book was created:\t" + book);
        assertEquals("Book3{id=50, quantity=1, itemType=BOOK, title='Matilda', price=20.0, description='About a girl', isbnNumber='1333213213211', initDate=Mon Dec 19 19:00:00 CET 2016, illustrations=false, numberOfPages=400, version=1, editor='Cappelen', author='Roald Dahl', year=1990}", result.toString());
        Assert.assertTrue(result.getId() > 0);
    }

    @Test
    public void getAllBooksTest() {
        List<Book3> bookList = jpaBookDao.getAllBooks();
        System.out.println("---BOOKS IN TABLE---");
        bookList.forEach(System.out::println);
        assertEquals(3, bookList.size());
    }

    @Test
    public void updateABook2ThatHasWrongEditorNameInputtedBySQL() throws Exception {
        Book3 book = jpaBookDao.findBookById(1);

        assertEquals("Rowling J.K", book.getAuthor());
        book.setAuthor("Joanne Kathleen Rowling");
        book.setNumberOfPages(309);

        entityManager.getTransaction().begin();
        boolean updated = jpaBookDao.update(book);
        entityManager.getTransaction().commit();

        assertTrue(updated);
        assertEquals("309", jpaBookDao.findBookById(1).getNumberOfPages().toString());
        assertEquals("Joanne Kathleen Rowling", jpaBookDao.findBookById(1).getAuthor());

    }

    @Test
    public void updateAndCreateANewBook() throws Exception {
        Book3 newBook = new Book3(1, ItemType.BOOK, "BFG", 199.0f, "BFG - big friendly gigant is about a nice gigant that put dreams into children's houses.", "9788205337961", new Date("06/30/2016 18:00:00"), true, 224, 1, "Roald Dahl", "Cappelen", 1982);
        assertEquals(3, jpaBookDao.getAllBooks().size());

        entityManager.getTransaction().begin();
        jpaBookDao.persist(newBook);
        entityManager.getTransaction().commit();

        Book3 findById = jpaBookDao.findBookById(50);

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

        Book3 getBookOne = jpaBookDao.findBookById(1);

        assertEquals("Book3{id=1, quantity=1, itemType=BOOK, title='Harry Potter and the seven wise stones', price=199.0, description='Harry Potter and the seven wise stone is a wonderful illustrated edition.', isbnNumber='9788202459772', initDate=2015-10-10 10:00:00.0, illustrations=true, numberOfPages=350, version=1, editor='Cappelen', author='Rowling J.K', year=2015}", getBookOne.toString());

        entityManager.getTransaction().begin();
        boolean delete = jpaBookDao.delete(getBookOne.getId());
        entityManager.getTransaction().commit();

        assertTrue(delete);
        assertNull(jpaBookDao.findBookById(1));
        assertEquals(null, jpaBookDao.findBookById(1));
        assertNotEquals("Book3{id=1, quantity=1, itemType=BOOK, title='Harry Potter and the seven wise stones', price=199.0, description='Harry Potter and the seven wise stone is a wonderful illustrated edition.', isbnNumber='9788202459772', initDate=2015-10-10 10:00:00.0, illustrations=true, numberOfPages=350, version=1, editor='Rowling J.K', year=2015}", jpaBookDao.findBookById(1));
    }

    /*
   CRITERIA BUILDER GET ALL BOOKS TEST
    */
    @Test
    public void getAllBooksWithCriteriaBuilder() throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book3> criteriaQuery = criteriaBuilder.createQuery(Book3.class);
        Root<Book3> from = criteriaQuery.from(Book3.class);

        CriteriaQuery<Book3> select = criteriaQuery.select(from);
        TypedQuery<Book3> query = entityManager.createQuery(select);
        List<Book3> allItems = query.getResultList();

        allItems.forEach(System.out::println);

        assertNotNull(allItems);
        assertEquals(3, allItems.size());
    }

    //TODO FIX!

    @Test @Ignore
    public void shouldCallANamedStoredProcedureQuery() throws Exception {

        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("archiveOldBooks");

        query.setParameter("archiveDate", new Date());
        query.setParameter("maxBookArchived", 1000);
        query.execute();
    }
/*
    @Test @Ignore
    public void shouldCallAStoredProcedureQuery() throws Exception {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_archive_old_books");
        query.registerStoredProcedureParameter("archiveDate", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("maxBookArchived", Integer.class, ParameterMode.IN);

        query.setParameter("archiveDate", new Date());
        query.setParameter("maxBookArchived", 1000);
        query.execute();
    }
*/
    @Test
    public void shouldCheckThatABookisCacheableEqualseFalse() throws Exception {

        Book3 newBook = new Book3();
        newBook.setQuantity(1);
        newBook.setItemType(ItemType.BOOK);
        newBook.setAuthor("Roald Dahl");
        newBook.setEditor("Cappelen");
        newBook.setDescription("BFG - big friendly gigant is about a nice gigant that put dreams into children's houses.");
        newBook.setTitle("BFG");
        newBook.setPrice(199.0f);
        newBook.setIsbnNumber("9788205337961");
        newBook.setInitDate(new Date("06/30/2016 18:00:00"));
        newBook.setIllustrations(true);
        newBook.setNumberOfPages(224);
        newBook.setVersion(1);
        newBook.setYear(1982);

        entityManager.getTransaction().begin();
        jpaBookDao.persist(newBook);
        entityManager.getTransaction().commit();

        assertNotNull(newBook.getId());
        Cache cache = factory.getCache();

        assertFalse(cache.contains(Book3.class, newBook.getId()));
    }




}
