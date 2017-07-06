package no.phasfjo.controller.book;

import no.phasfjo.dto.book.Book;
import no.phasfjo.infrastructure.book.BookDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Model
public class BookController {

    @Inject
    private BookDao bookDao;
    private int selectedId;
    private Book book;


    //----------------------------------
    //  CONSTRUCTOR                    -
    //----------------------------------

    @PostConstruct
    public void init() {
        book = new Book();
    }

    //----------------------------------
    //  METHODS                        -
    //----------------------------------

    public void persist() {
        bookDao.persist(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Book findBookById() {
        return bookDao.findBookById(selectedId);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete() {
        bookDao.delete(selectedId);
    }

    //----------------------------------
    //  GET AND SET                    -
    //----------------------------------


    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
