package no.phasfjo.infrastructure.address;

import no.phasfjo.dto.address.Address;

import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
public interface AddressDao {

    Address persist(Address address);

    List<Address> getAll();

    boolean update(Address address);

    Address findById(int id);

}
