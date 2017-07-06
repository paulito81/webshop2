package no.phasfjo.infrastructure.customer;

import no.phasfjo.dto.customer.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.customer.Customer.GET_ALL_CUSTOMERS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@JpaCustomer
@Stateless
public class JpaCustomerDao implements CustomerDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaCustomerDao() {
    }

    public JpaCustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer persist(Customer customer) {
        if (customer == null)
            throw new IllegalArgumentException("No customer could be created!");
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Customer findById(int id) {
        if (id != 0) {
            return entityManager.find(Customer.class, id);
        } else
            throw new IllegalArgumentException("An error has occurred no customer with id:" + id + " were found");
    }

    @Override
    public boolean update(Customer customer) {
        if (customer == null)
            throw new IllegalArgumentException(("No customer could be updated"));
        if (!entityManager.contains(customer)) {
            entityManager.merge(customer);
        }
        return true;
    }

    @Override
    public List<Customer> getAll() {
        TypedQuery<Customer> query = entityManager.createNamedQuery(GET_ALL_CUSTOMERS, Customer.class);
        return query.getResultList();
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);
            return true;
        }
        throw new IllegalArgumentException(String.format("Customer with id-nr:{%d] could not be deleted =C ", id));
    }


}
