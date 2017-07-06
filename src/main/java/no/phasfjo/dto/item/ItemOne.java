package no.phasfjo.dto.item;

import no.phasfjo.constraint.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.item.ItemOne.GET_ALL_ITEM_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_ITEM_ONE, query = "select i from ItemOne i")
@SequenceGenerator(name = "SEQ_ITEM_ONE", initialValue = 50)
public class ItemOne {

    public static final String GET_ALL_ITEM_ONE = "ItemOne.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM_ONE")
    private int id;

    //-----------------------------------------
    //--        ATTRIBUTES                   --
    //-----------------------------------------

    @NotNull
    @Description2
    private String itemName;
    @NotNull
    @AllItemTypes
    private ItemType itemType;
    @NotNull
    @Price
    private Float price;
    @NotNull
    @Description
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @DateVal2
    private Date date;

    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemOne() {
    }

    public ItemOne(String itemName, ItemType itemType, Float price, String description, Date date) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.description = description;
        this.date = date;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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

    public Date getInstantiate() {
        return date;
    }

    public void setInstantiate(Date instantiate) {
        this.date = instantiate;
    }

    // ======================================
    // =          TO STRING                 =
    // ======================================


    @Override
    public String toString() {
        return "ItemOne{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemType=" + itemType +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
