package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;
import no.phasfjo.dto.item.ItemType;
import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Book3_1IT {


    private static EJBContainer ejbContainer;
    private static Context context;


    @BeforeClass
    public static void initContainer() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ejbContainer = EJBContainer.createEJBContainer(properties);
        context = ejbContainer.getContext();
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (context != null)
            context.close();
        if (ejbContainer != null)
            ejbContainer.close();
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test @Ignore
    public void shouldCreateABook() throws Exception {
        Date date = new Date();
        Book3 newBook = new Book3();
        newBook.setQuantity(1);
        newBook.setItemType(ItemType.BOOK);
        newBook.setInitDate(date);
        newBook.setDescription("BFG - big friendly gigant is about a nice gigant that put dreams into children's houses.");
        newBook.setTitle("BFG");
        newBook.setPrice(199.0f);
        newBook.setIsbnNumber("9788205337961");
        newBook.setNumberOfPages(222);
        newBook.setIllustrations(true);
        newBook.setAuthor("Roald Dahl");
        newBook.setVersion(1);
        newBook.setYear(1995);

        Assert.assertNotNull(context.lookup("java:global/classes/JpaBook3StatelessDao!no.phasfjo.infrastructure.book.JpaBook3StatelessDao"));
        Assert.assertNotNull(context.lookup("java:global/classes/JpaBook3StatelessDao"));

        JpaBook3StatelessDao book3Dao = (JpaBook3StatelessDao) context.lookup("java:global/classes/JpaBook3StatelessDao!no.phasfjo.infrastructure.book.JpaBook3StatelessDao");
        newBook = book3Dao.persist(newBook);
        Assert.assertNotNull("ID should not be null ", newBook.getId());
        Assert.assertNotNull(book3Dao.findAllBooks());
    }
}
