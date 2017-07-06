package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book3;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by paulhasfjord on 22.12.2016.
 */

@Local
public interface Book3LocalDao {

    List<Book3> findAllBooks();
}
