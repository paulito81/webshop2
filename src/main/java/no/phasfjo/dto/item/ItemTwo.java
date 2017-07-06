package no.phasfjo.dto.item;

import no.phasfjo.constraint.Description;
import no.phasfjo.constraint.Item;
import no.phasfjo.constraint.MinSize;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.item.ItemTwo.GET_ALL_ITEM_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_ITEM_TWO, query = "select i from ItemTwo i")
@SequenceGenerator(name = "SEQ_ITEM_TWO", initialValue = 50)
public class ItemTwo extends ItemOne{

    public static final String GET_ALL_ITEM_TWO = "ItemTwo.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM_TWO")
    private int id;

    //-----------------------------------------
    //--        ATTRIBUTES                   --
    //-----------------------------------------

    @NotNull
    @Item
    private String color;
    @NotNull
    @MinSize
    private Double height;
    @NotNull
    @MinSize
    private Double width;
    @NotNull
    @MinSize
    private Double weight;
    @NotNull
    @Description
    private String manufacturernumber;
    @NotNull
    @Min(1)
    private Integer quantity;


    //-----------------------------------------
    //--        CONSTRUCTORS                 --
    //-----------------------------------------

    public ItemTwo() {
    }

    public ItemTwo(String itemName, ItemType itemType, Float price, String description, Date date, String color, Double height, Double width, Double weight, String manufacturernumber, Integer quantity) {
        super(itemName, itemType, price, description, date);
        this.color = color;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.manufacturernumber = manufacturernumber;
        this.quantity = quantity;
    }

    //-----------------------------------------
    //--        GET AND SET                  --
    //-----------------------------------------


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getManufacturernumber() {
        return manufacturernumber;
    }

    public void setManufacturernumber(String manufacturernumber) {
        this.manufacturernumber = manufacturernumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //-----------------------------------------
    //--        TO STRING                    --
    //-----------------------------------------

    @Override
    public String toString() {
        return "ItemTwo{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", weight=" + weight +
                ", manufacturernumber='" + manufacturernumber + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
