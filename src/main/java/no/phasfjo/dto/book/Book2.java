package no.phasfjo.dto.book;



import no.phasfjo.constraint.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
@Entity
@NamedQuery(name = "Book2.getAllBooks", query = "select b from Book2 b")
@SequenceGenerator(name = "SEQ_BOOK2", initialValue = 50)
public class Book2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK2")
    private int id;

    //--------------------------------------
    //         ATTRIBUTES                  |
    //--------------------------------------

    @NotNull
    @Title
    private String title;
    @NotNull
    @Price
    private Float price;
    @NotNull
    @Description
    private String description;
    @NotNull
    @Isbn
    private String isbnNumber;
    @NotNull
    @InitDate
    private Date initDate;
    @NotNull(message = "Invalid illustration is set to null")
    private Boolean illustrations;
    @NotNull
    @PageNumber
    private Integer numberOfPages;
    @NotNull
    @Min(1)
    private Integer version;
    @NotNull
    @Editor
    private String editor;
    @NotNull
    @Min(1519)
    private Integer year;
    @NotNull
    @Author
    private String author;

    //--------------------------------------
    //         CONSTRUCTORS                |
    //--------------------------------------

    public Book2() {
    }

    public Book2(String title, Float price, String description, String isbnNumber, Date initDate, Boolean illustrations, Integer numberOfPages, Integer version, String author, String editor, Integer year) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbnNumber = isbnNumber;
        this.initDate = initDate;
        this.illustrations = illustrations;
        this.numberOfPages = numberOfPages;
        this.version = version;
        this.author = author;
        this.editor = editor;
        this.year = year;
    }

    //--------------------------------------
    //         GET AND SET                 |
    //--------------------------------------

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
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

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    @Override
    public String toString() {
        return "Book2{" +
                "id=" + id +
                ", version=" + version +
                ", illustrations=" + illustrations +
                ", numberOfPages=" + numberOfPages +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", initDate=" + initDate +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", year=" + year +
                '}';
    }
}
