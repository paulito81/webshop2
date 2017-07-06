package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;

import java.util.List;

/**
 * Created by paulhasfjord on 19.12.2016.
 */
public interface Book3Dao {

    Book3 persist(Book3 book);

    List<Book3> getAllBooks();

    Book3 findBookById(int id);

    boolean update(Book3 book);

    boolean delete(int id);
}
