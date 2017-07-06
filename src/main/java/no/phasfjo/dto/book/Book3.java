package no.phasfjo.dto.book;


import no.phasfjo.constraint.*;
import no.phasfjo.dto.item.ItemType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.book.Book3.FIND_ALL_BOOKS;

/**
 * Created by paulhasfjord on 19.12.2016.
 */
@Entity
/*@NamedStoredProcedureQuery(name = "archiveOldBooks", procedureName = "sp_archive_books",
        parameters = {
                @StoredProcedureParameter(name = "archiveDate", mode = ParameterMode.IN, type = Date.class),
                @StoredProcedureParameter(name = "warehouse", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "maxBookArchived", mode = ParameterMode.INOUT, type = Integer.class)
        })
*/
@Cacheable(false)
@NamedQuery(name = FIND_ALL_BOOKS, query = "select b from Book3 b")
@SequenceGenerator(name = "SEQ_BOOK3", initialValue = 50)
public class Book3 {

    // ======================================
    // =            CONSTANTS               =
    // ======================================

    public static final String FIND_ALL_BOOKS = "Book3.findAllBooks";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK3")
    private int id;

    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    @AllItemTypes
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
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
    @Version
    @Min(1)
    private Integer version;
    @NotNull
    @Author
    private String author;
    @Editor
    private String editor;
    @NotNull
    @Min(1519)
    private Integer year;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Book3() {
    }

    public Book3(Integer quantity, ItemType itemType, String title, Float price, String description, String isbnNumber, Date initDate, Boolean illustrations, Integer numberOfPages, Integer version, String author, String editor, Integer year) {
        this.quantity = quantity;
        this.itemType = itemType;
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

    // ======================================
    // =            GET AND SET             =
    // ======================================

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String editor) {
        this.author = editor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    // ======================================
    // =          TO STRING                 =
    // ======================================


    @Override
    public String toString() {
        return "Book3{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", initDate=" + initDate +
                ", illustrations=" + illustrations +
                ", numberOfPages=" + numberOfPages +
                ", version=" + version +
                ", editor='" + editor + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
