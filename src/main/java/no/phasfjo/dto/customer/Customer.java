package no.phasfjo.dto.customer;

import no.phasfjo.constraint.*;
import no.phasfjo.dto.account.Account;
import no.phasfjo.dto.address.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static no.phasfjo.dto.customer.Customer.GET_ALL_CUSTOMERS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */


@Entity
@Table(name = "customer")
@NamedQuery(name = GET_ALL_CUSTOMERS, query = "SELECT c FROM Customer c")
@Cacheable()
@SequenceGenerator(name = "SEQ_CUST", initialValue = 50)
public class Customer{

    public static final String GET_ALL_CUSTOMERS = "Customer.getAll";

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUST")
    @Column(name = "FK_CUSTOMER")
    private int id;
    @NotNull
    @Title(message = "Invalid name must be 3 - 50 letters")
    private String firstName;
    @NotNull
    @Title(message = "Invalid name must be 3 - 50 letters")
    private String lastName;
    @NotNull
    @Phone
    private String phone;
    @NotNull
    @Email
    private String email;
    @NotNull
    @InitDate
    private Date birth;

    //TODO CREATE A NEW CUSTOMER LAYOUT @RolesAllowed
    // http://www.oracle.com/technetwork/articles/javaee/security-annotation-142276.html
/*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ACCOUNT")
    private Account account;
*/
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ADDRESS")
    private Address address;

    // ======================================
    // =            Constructors            =
    // ======================================


    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phone, Date birth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

/*
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
*/
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    // ======================================
    // =            TO STRING           =
    // ======================================

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phone + '\'' +
                ", birth=" + birth +
                ", address='" + address + '\'' +
                '}';
    }
}
