package no.phasfjo.view;


import no.phasfjo.dto.customer.Customer;
import org.primefaces.event.FlowEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by paulhasfjord on 12.01.2017.
 */
public class CustomerWizard implements Serializable{

    private Customer customer = new Customer();

    private boolean skip;


    // ======================================
    // =           METHOD                   =
    // ======================================

    public void save() {

        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + customer.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {

        this.skip = skip;
    }


    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    // =====================================
    // =          Getters & Setters         =
    // ======================================

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
