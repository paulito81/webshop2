package no.phasfjo.dto.address;

import no.phasfjo.constraint.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import static no.phasfjo.dto.address.Address.GET_ALL_ADDRESS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Entity
@Table(name = "address")
@NamedQuery(name = GET_ALL_ADDRESS, query = "select a from Address a")
@Cacheable()
@SequenceGenerator(name = "SEQ_ADR", initialValue = 50)
public class Address {

    public static final String GET_ALL_ADDRESS = "Address.getAll";

    @Id
    @Column(name = "FK_ADDRESS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADR")
    private int id;

    @NotNull
    @Street
    private String street;
    @NotNull
    @City
    private String city;
    @NotNull
    @State
    private String state;
    @NotNull
    @ZipCode
    private String zipcode;
    @NotNull
    @Country
    private String country;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Address() {
    }

    public Address(String street, String city, String state, String zipcode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Address(int id, String street, String city, String state, String zipcode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    // =====================================
    // =          Getters & Setters         =
    // ======================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipode) {
        this.zipcode = zipode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // ======================================
    // =          To String                 =
    // ======================================

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
