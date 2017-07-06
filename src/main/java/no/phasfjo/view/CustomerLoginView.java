package no.phasfjo.view;

import org.primefaces.context.RequestContext;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Date;

/**
 * Created by paulhasfjord on 24.01.2017.
 */
@Model
public class CustomerLoginView {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerLoginView() {
    }

    public CustomerLoginView(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;

        //TODO SWITCH-MENU GET INFO FROM SCRIPT/ DB
        /*
        http://www.primefaces.org/showcase/ui/overlay/dialog/loginDemo.xhtml
         */
        switch (username) {

            case "admin":
                if (username.equals("admin") && password != null && password.equals("admin")) {
                    loggedIn = true;
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome back ", username + "!\n\n" + new Date() + ".\n\nLogged in: " + loggedIn);
                }
                break;

            case "paulito81":
                if (username.equals("paulito81") && password != null && password.equals("apeLars123$@#")) {
                    loggedIn = true;
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome back ", username + "!\n\n" + new Date() + ".\n\nLogged in: " + loggedIn);
                }
                break;
            default:
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Logging Error", "Invalid credentials");

        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }
}

