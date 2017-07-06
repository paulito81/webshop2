package no.phasfjo.controller.login;

import no.phasfjo.dto.login.Login;
import no.phasfjo.infrastructure.login.JpaLogin;
import no.phasfjo.infrastructure.login.LoginDao;
import no.phasfjo.view.CustomerLoginView;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Model
public class LoginController {

    //@Inject
    @JpaLogin
    private LoginDao loginDao;
    private int selectedId;
    private Login login;

    private CustomerLoginView customerLoginView;
    //----------------------------------
    //  CONSTRUCTOR                    -
    //----------------------------------

    @PostConstruct
    public void init() {
        login = new Login();
    }

    //----------------------------------
    //  METHODS                        -
    //----------------------------------

    public void persist() {
        loginDao.persist(login);
    }

    public void update() {
        loginDao.update(login);
    }

    //----------------------------------
    //      GET AND SET                -
    //----------------------------------

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public CustomerLoginView getCustomerLoginView() {
        return customerLoginView;
    }

    public void setCustomerLoginView(CustomerLoginView customerLoginView) {
        this.customerLoginView = customerLoginView;
    }
}
