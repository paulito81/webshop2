package no.phasfjo.dto.image;

import no.phasfjo.constraint.Description;
import no.phasfjo.constraint.Item;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;

import static no.phasfjo.dto.image.Image.GET_ALL_IMAGES;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_IMAGES, query = "select i from Image i")
@SequenceGenerator(name = "SEQ_IMG", initialValue = 50)
public class Image {

    public static final String GET_ALL_IMAGES = "Image.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMG")
    private int id;
    @Item
    private String name;
    @Description
    private String description;
    @NotNull
    private File picture;

    // ======================================
    // =            CONSTRUCTOR             =
    // ======================================

    public Image() {

    }

    public Image(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Image(String name, String description, File picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    // ======================================
    // =            GET AND SET            =
    // ======================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }
}
