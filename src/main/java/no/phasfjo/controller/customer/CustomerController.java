package no.phasfjo.controller.customer;

import no.phasfjo.dto.account.Account;
import no.phasfjo.dto.address.Address;
import no.phasfjo.dto.customer.Customer;
import no.phasfjo.infrastructure.customer.CustomerDao;
import no.phasfjo.infrastructure.customer.JpaCustomer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Model
public class CustomerController {

    @JpaCustomer
//    @Inject

    //TODO DO I NEED TWO INJECTIONS?
    private CustomerDao customerDao;
    private int selectedId;

    private Address address;
    private Account account;
    private Customer customer;
    private int generatedID = 0;

    //----------------------------------
    //  CONSTRUCTOR                    -
    //----------------------------------

    @PostConstruct
    public void init() {
        customer = new Customer();
    }

    //----------------------------------
    //  METHODS                        -
    //----------------------------------

    public void persist() {
        customerDao.persist(customer);
    }

    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    public Customer findById() {
        return customerDao.findById(selectedId);
    }

    public void update() {
        customerDao.update(customer);
    }

    public void delete() {
        customerDao.delete(selectedId);
    }


    public List<SelectItem> getCustomers() {
        List<Customer> customers = customerDao.getAll();
        return customers.stream().map(c -> new SelectItem(c.getId(), c.getFirstName(), c.getLastName())).collect(Collectors.toList());
    }

    /*
    Generate id start at 1.
     */
    public int generateID(){
        generatedID++;
        return generatedID;
    }

    //TODO HOW TO SET USERNAME, PASSWORD FROM ANOTHER METHOD.

    //----------------------------------
    //  GET AND SET                    -
    //----------------------------------

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

