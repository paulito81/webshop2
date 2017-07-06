package no.phasfjo.dto.account;

import no.phasfjo.dto.customer.Customer;
import no.phasfjo.dto.login.Login;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import static no.phasfjo.dto.account.Account.GET_ALL_ACCOUNTS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_ACCOUNTS, query = "select a from Account a")
@SequenceGenerator(name = "SEQ_ACC", initialValue = 50)
public class Account {

    public static final String GET_ALL_ACCOUNTS = "Account.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACC")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @CascadeOnDelete
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "FK_CUSTOMER")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    @CascadeOnDelete
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "FK_LOGIN")
    private Login login;

    // ======================================
    // =            CONSTRUCTORS          =
    // ======================================

    public Account(Customer customer, Login login) {
        this.customer = customer;
        this.login = login;
    }

    public Account() {

    }
    // ======================================
    // =            GET AND SET            =
    // ======================================

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    // ======================================
    // =            TO STRING        =
    // ======================================

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customer=" + customer +
                ", login= '" + login +
                '}';
    }
}
