package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book4;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by paulhasfjord on 02.01.2017.
 */
@Remote
public interface Book4Remote {

    Book4 persist(Book4 book);

    List<Book4> findAllBooks();
}
