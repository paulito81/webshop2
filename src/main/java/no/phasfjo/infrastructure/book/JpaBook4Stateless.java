package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book4;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.book.Book4.FIND_ALL;

/**
 * Created by paulhasfjord on 02.01.2017.
 */
@Stateless
@LocalBean
public class JpaBook4Stateless implements Book4Remote, Book4Local{

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager em;

    @Override
    public Book4 persist(Book4 book) {
        em.persist(book);
        return book;
    }

    @Override
    public List<Book4> findAllBooks() {
        TypedQuery<Book4> query = em.createNamedQuery(FIND_ALL, Book4.class);
        em.joinTransaction();
        return query.getResultList();
    }



}
