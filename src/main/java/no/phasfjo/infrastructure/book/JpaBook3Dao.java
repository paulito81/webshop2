package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.book.Book3.FIND_ALL_BOOKS;

/**
 * Created by paulhasfjord on 19.12.2016.
 */
@Stateless
public class JpaBook3Dao implements Book3Dao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaBook3Dao(){
    }
    public JpaBook3Dao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Book3 persist(Book3 book) {
        if (book == null)
            throw new IllegalArgumentException("No book could be created!");
        entityManager.persist(book);
        return book;
    }

    @Override
    public List<Book3> getAllBooks() {
        TypedQuery<Book3> query = entityManager.createNamedQuery(FIND_ALL_BOOKS, Book3.class);
        return query.getResultList();
    }

    @Override
    public Book3 findBookById(int id) {
        if (id != 0) {
            return entityManager.find(Book3.class, id);
        } else
            throw new IllegalArgumentException("Error no book with with id: " + id + " were found");

    }

    @Override
    public boolean update(Book3 book) {
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
            Book3 book = entityManager.find(Book3.class, id);
            entityManager.remove(book);
            return true;
        }
        throw new IllegalArgumentException(String.format("Book with id-nr:{%d] could not be deleted =C ", id));
    }
}
