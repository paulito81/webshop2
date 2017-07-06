package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
public class JpaBook2Dao implements Book2Dao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaBook2Dao() {

    }

    public JpaBook2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book2 persist(Book2 book) {
        if (book == null)
            throw new IllegalArgumentException("No book could be created!");
        entityManager.persist(book);
        return book;
    }

    @Override
    public List<Book2> getAllBooks() {
        TypedQuery<Book2> query = entityManager.createNamedQuery("Book2.getAllBooks", Book2.class);
        return query.getResultList();
    }

    @Override
    public Book2 findBookById(int id) {
        if (id != 0) {
            return entityManager.find(Book2.class, id);
        } else
            throw new IllegalArgumentException("Error no book with with id: " + id + " were found");
    }
    @Override
    public boolean update(Book2 book){
        if (book == null)
            throw new IllegalArgumentException(("No book could be updated"));
        if(!entityManager.contains(book)){
            entityManager.merge(book);
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            Book2 book = entityManager.find(Book2.class, id);
            entityManager.remove(book);
            return true;
        }
        throw new IllegalArgumentException(String.format("Book with id-nr:{%d] could not be deleted =C ", id));
    }

}
