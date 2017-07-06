package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by paulhasfjord on 22.12.2016.
 */

@Stateless
@LocalBean
public class JpaBook3StatelessDao implements Book3LocalDao, Book3RemoteDao {


    // ======================================
    // =             Attributes             =
    // ======================================


    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;
    // ======================================
    // =             Public Methods         =
    // ======================================


    @Override
    public Book3 persist(Book3 book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public List<Book3> findAllBooks() {
        TypedQuery<Book3> query = entityManager.createNamedQuery("Book3.findAllBooks", Book3.class);
        return query.getResultList();
    }

    public Book3 findBookById(int id){
        return entityManager.find(Book3.class, id);
    }
    public void deleteBook(Book3 book){
        entityManager.remove(entityManager.merge(book));
    }
    public Book3 updateBook(Book3 book){
        return  entityManager.merge(book);
    }
}
