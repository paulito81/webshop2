package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.book.Book.FIND_ALL_BOOKS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Stateless
public class JpaBookDao implements BookDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaBookDao(){

    }
    public JpaBookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book persist(Book book) {
        if (book == null)
            throw new IllegalArgumentException("No book could be created!");
        entityManager.persist(book);
        return book;
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = entityManager.createNamedQuery(FIND_ALL_BOOKS, Book.class);
        return query.getResultList();
    }

    public Book findBookById(int id) {
        if (id != 0) {
            return entityManager.find(Book.class, id);
        } else
            throw new IllegalArgumentException("Error no book with with id: " + id + " were found");

    }

    public boolean update(Book book) {
        if (book == null)
            throw new IllegalArgumentException(("No book could be updated"));
        if (!entityManager.contains(book)) {
            entityManager.merge(book);
        }
        return true;
    }

    public boolean delete(int id) {
        if (id != 0) {
            Book book = entityManager.find(Book.class, id);
            entityManager.remove(book);
            return true;
        }
        throw new IllegalArgumentException(String.format("book with id-nr:{%d] could not be deleted =C ", id));
    }
}
