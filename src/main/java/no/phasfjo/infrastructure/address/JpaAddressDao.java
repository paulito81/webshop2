package no.phasfjo.infrastructure.address;

import no.phasfjo.dto.address.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.address.Address.GET_ALL_ADDRESS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Stateless
public class JpaAddressDao  implements AddressDao{

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaAddressDao() {

    }

    public JpaAddressDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Address persist(Address address) {
        if (address == null)
            throw new IllegalArgumentException("No address could be created!");
        entityManager.persist(address);
        return address;
    }

    @Override
    public List<Address> getAll() {
        TypedQuery<Address> query = entityManager.createNamedQuery(GET_ALL_ADDRESS, Address.class);
        return query.getResultList();
    }

    @Override
    public boolean update(Address address) {
        if (address == null) {
            return false;
        }
        if (!entityManager.contains(address)) {
            entityManager.merge(address);
        }
        return true;
    }

    @Override
    public Address findById(int id) {
        if (id == 0 || id < 0)
            throw new IllegalArgumentException("No address was found!");
        return entityManager.find(Address.class, id);
    }
}
