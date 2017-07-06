package no.phasfjo.controller.address;

import no.phasfjo.dto.address.Address;
import no.phasfjo.infrastructure.address.AddressDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by paulhasfjord on 22.01.2017.
 */
@Model
public class AddressController {

    @Inject
    private AddressDao addressDao;
    private int selectedId;

    private Address address;

    @PostConstruct
    public void init() {
        address = new Address();
    }

    //---------------------------------
    //   METHODS                      -
    //---------------------------------

    public void persist(){
        addressDao.persist(address);
    }

    public boolean update(Address address){
        return addressDao.update(address);
    }

    public Address findById(){
        return addressDao.findById(selectedId);
    }

    public List<Address> getAll(){
        return addressDao.getAll();
    }

    //---------------------------------
    //  GET AND SET                   -
    //---------------------------------


    public AddressDao getAddressDao() {
        return addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
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
}
