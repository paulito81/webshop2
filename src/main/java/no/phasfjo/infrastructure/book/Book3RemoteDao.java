package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by paulhasfjord on 22.12.2016.
 */

@Remote
public interface Book3RemoteDao {

    Book3 persist(Book3 book);

    List<Book3> findAllBooks();

}

