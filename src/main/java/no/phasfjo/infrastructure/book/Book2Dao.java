package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book2;

import java.util.List;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
public interface Book2Dao {

    Book2 persist(Book2 book);

    List<Book2> getAllBooks();

    Book2 findBookById(int id);

    boolean update(Book2 book);

    boolean delete(int id);
}
