package no.phasfjo.infrastructure.customer;

import no.phasfjo.dto.customer.Customer;

import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public interface CustomerDao {

    Customer persist(Customer customer);

    Customer findById(int id);

    boolean update(Customer customer);

    List<Customer> getAll();

    boolean delete(int id);
}
