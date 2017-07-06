package no.phasfjo.dto.order;

import no.phasfjo.constraint.ChronologicalDates;
import no.phasfjo.constraint.Price;
import no.phasfjo.dto.orderline.OrderLine;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static no.phasfjo.dto.order.Order.GET_ALL_ORDER_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@ChronologicalDates
@Table(name = "Order_")
@NamedQuery(name = GET_ALL_ORDER_ONE, query = "SELECT o FROM Order o")
@SequenceGenerator(name = "SEQ_ORD", initialValue = 50)
public class Order {


    public static final String GET_ALL_ORDER_ONE = "Order.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORD")
    private int id;

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    private Date creationDate;
    @NotNull
    @Min(1)
    private Double totalAmount;
    @NotNull
    @Future
    private Date paymentDate;
    @NotNull
    @Future
    private Date deliveryDate;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    private final Double MIN_PRICE = 1d;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    private List<OrderLine> orderLines;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Order() {
        this.creationDate = new Date();
    }

    public Order(Date creationDate, Double totalAmount, Date paymentDate, Date deliveryDate, Integer quantity, List<OrderLine> orderLines) {
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
        this.orderLines = orderLines;
    }

    public Order(Date creationDate, Double totalAmount, Date paymentDate, Date deliveryDate, Integer quantity) {
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.deliveryDate = deliveryDate;
        this.quantity = quantity;
    }

    // ======================================
    // =            Methods                 =
    // ======================================

    @NotNull
    public Double calculateTotalAmount(@Price Double changeRate) {
        return MIN_PRICE;
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

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", totalAmount=" + totalAmount +
                ", paymentDate=" + paymentDate +
                ", deliveryDate=" + deliveryDate +
                ", quantity=" + quantity +
                ", orderLines=" + orderLines +
                '}';
    }
}
