package no.phasfjo.dto.book;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static no.phasfjo.dto.book.Book4.FIND_ALL;

/**
 * Created by paulhasfjord on 02.01.2017.
 */
@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT b FROM Book4 b")
@SequenceGenerator(name = "SEQ_BOOK4", initialValue = 50)
public class Book4 {

    // ======================================
    // =             Constants              =
    // ======================================

    public static final String FIND_ALL = "Book4.findAllBooks";

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK4")
    private Long id;
    @NotNull
    private String title;
    private Float price;
    @Size(max = 2000)
    private String description;
    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Book4() {
    }

    public Book4(String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

}