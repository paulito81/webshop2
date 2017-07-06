package no.phasfjo.infrastructure.book;

import no.phasfjo.dto.book.Book4;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by paulhasfjord on 02.01.2017.
 */
@Local
public interface Book4Local {

    List<Book4> findAllBooks();
}
