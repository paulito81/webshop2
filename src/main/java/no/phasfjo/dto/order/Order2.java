package no.phasfjo.dto.order;

import no.phasfjo.constraint.Price;
import no.phasfjo.dto.orderline.OrderLine2;
import org.eclipse.persistence.annotations.TimeOfDay;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static no.phasfjo.dto.order.Order2.*;

/**
 * Created by phasf on 27.01.2017.
 */

@Entity
@Table(name = "Order2_")
@NamedQueries({
        @NamedQuery(name = GET_ALL_ORDER_TWO, query = "SELECT o FROM Order2 o"),
        @NamedQuery(name = GET_TOTAL_SUM_ORDER_TWO, query = "SELECT SUM(totalAmount) FROM Order2"),
        @NamedQuery(name = GET_ALL_DETACHED_ORDER_TWO, query = "SELECT o FROM OrderLine2 o WHERE o.id = :id"),

})
@SequenceGenerator(name = "SEQ_ORDER2", initialValue = 50)
public class Order2 {

    public static final String GET_ALL_ORDER_TWO = "Order2.getALl";
    public static final String GET_ALL_DETACHED_ORDER_TWO = "Order2.getAllDetached";
    public static final String GET_TOTAL_SUM_ORDER_TWO = "Order2.getSumOfAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER2")
    private int id;

    //-----------------------------------------
    //--        ATTRIBUTES                   --
    //-----------------------------------------

    @NotNull
    @CreationTimestamp()
    private Date toDaysDate;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @Min(1)
    private Double totalAmount;

    @NotNull
    private final Double MIN_PRICE = 1d;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order2_fk")
    private List<OrderLine2> orderLines;

    public Order2() {

    }

    public Order2(Integer quantity, Double totalAmount, List<OrderLine2> orderLines, Date toDaysDate) {
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderLines = orderLines;
        this.toDaysDate = toDaysDate;
    }
    // ======================================
    // =            Methods                 =
    // ======================================

    @NotNull
    public Double calculateTotalAmount(@Price Double changeRate) {
        return MIN_PRICE;
    }


    //-----------------------------------------
    //--        GET AND SET                  --
    //-----------------------------------------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getToDaysDate() {
        return toDaysDate;
    }

    public void setToDaysDate(Date toDaysDate) {
        this.toDaysDate = toDaysDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getMIN_PRICE() {
        return MIN_PRICE;
    }

    public List<OrderLine2> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine2> orderLines) {
        this.orderLines = orderLines;
    }

}
