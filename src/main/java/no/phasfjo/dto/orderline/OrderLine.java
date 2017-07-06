package no.phasfjo.dto.orderline;

import no.phasfjo.constraint.Item;
import no.phasfjo.constraint.MinQuantity;
import no.phasfjo.constraint.OrderPrice;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import static no.phasfjo.dto.orderline.OrderLine.GET_ALL_DETACHED_ORDER_LINE_ONE;
import static no.phasfjo.dto.orderline.OrderLine.GET_ALL_ORDER_LINES;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@Table(name = "Order_OrderLine")
@NamedQueries({
        @NamedQuery(name = GET_ALL_ORDER_LINES, query = "SELECT o FROM OrderLine o"),
        @NamedQuery(name = GET_ALL_DETACHED_ORDER_LINE_ONE, query = "SELECT o FROM OrderLine o WHERE o.id = :id"),
})
@SequenceGenerator(name = "SEQ_ORD_LINE", initialValue = 50)
public class OrderLine {

    public static final String GET_ALL_DETACHED_ORDER_LINE_ONE = "Orderline.getAllDetachedOrderLineOne";
    public static final String GET_ALL_ORDER_LINES = "OrderLine.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORD_LINE")
    private int id;

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @Item
    private String item;
    @NotNull
    @OrderPrice
    private Double unitPrice;
    @NotNull
    @MinQuantity
    private Integer quantity;

    // ======================================
    // =            Constructors            =
    // ======================================

    public OrderLine() {

    }

    public OrderLine(String item, Double unitPrice, Integer quantity) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    @NotNull
    @DecimalMin("5.8")
    public Double calculatePrice(@DecimalMin("1.4") Double rate) {
        return unitPrice * rate;
    }

    //CALCULATE ITEM VAT
    @DecimalMin("9.99")
    public Double calculateVAT() {
        return unitPrice * 0.196d;
    }

    // ======================================
    // =             Get and SET            =
    // ======================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    // ======================================
    // =            To String               =
    // ======================================

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
