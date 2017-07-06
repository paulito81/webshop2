package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book;

import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public interface BookDao {

    Book persist(Book book);
    List<Book> getAllBooks();
    Book findBookById(int id);
    boolean update(Book book);
    boolean delete(int id);
}
