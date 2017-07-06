package no.phasfjo.dto.orderline;

import no.phasfjo.constraint.ChronologicalDates2;
import no.phasfjo.constraint.Item;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.orderline.OrderLine2.*;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@ChronologicalDates2
@Table(name = "Order2_OrderLine2")
@NamedQueries({
        @NamedQuery(name = GET_ALL_ORDERLINES_TWO, query = "SELECT o FROM OrderLine2 o"),
        @NamedQuery(name = GET_ALL_DETACHED_ORDER_LINE_TWO, query = "SELECT o FROM OrderLine2 o WHERE o.id = :id"),
        @NamedQuery(name = GET_TOTAL_SUM_TWO, query = "SELECT SUM(unitPrice) FROM OrderLine2")
})
@SequenceGenerator(name = "SEQ_ORD2_LINE", initialValue = 50)
public class OrderLine2 {

    public static final String GET_ALL_ORDERLINES_TWO = "OrderLine2.getAll";
    public static final String GET_ALL_DETACHED_ORDER_LINE_TWO = "OrderLine2.getAllOrders";
    public static final String GET_TOTAL_SUM_TWO = "OrderLine2.getSumOfAllOrders";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORD2_LINE")
    private int id;

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    private Date creationDate;
    @NotNull
    @Future(message = "InitDate must be in the future")
    private Date paymentDate;
    @NotNull
    @Future(message = "InitDate must be in the future")
    private Date deliveryDate;
    @NotNull
    @Min(1)
    private Double unitPrice;
    @NotNull
    @Min(1)
    private Integer quantity;

    @Item
    @NotNull
    private String item;

    // ======================================
    // =            Constructors            =
    // ======================================

    public OrderLine2() {

    }

    public OrderLine2(Date creationDate, Date paymentDate, Date deliveryDate, Double unitPrice, Integer quantity, String item) {
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.deliveryDate = deliveryDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.item = item;
    }

    // ======================================
    // =            GET and SET            =
    // ======================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderLine2{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", paymentDate=" + paymentDate +
                ", deliveryDate=" + deliveryDate +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", item='" + item + '\'' +
                '}';
    }
}
