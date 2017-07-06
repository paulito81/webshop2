package no.phasfjo.controller.account;

import no.phasfjo.dto.account.Account;
import no.phasfjo.dto.customer.Customer;
import no.phasfjo.dto.login.Login;
import no.phasfjo.infrastructure.account.AccountDao;
import no.phasfjo.infrastructure.account.JpaAccount;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.List;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@Model
public class AccountController {

   // @Inject
    @JpaAccount
    private AccountDao accountDao;
    private int selectedId;

    private Account account;
    private Customer customer;
    private Login login;

    @PostConstruct
    public void init() {
        account = new Account();
    }

    //---------------------------------
    //   METHODS                      -
    //---------------------------------


    public void persist() {
        accountDao.persist(account);
    }

    public Boolean delete() {
        return accountDao.delete(selectedId);
    }

    public Account findById() {
        return accountDao.findById(selectedId);
    }

    public boolean update() {
        return accountDao.update(account);
    }

    public List<Account> getAll() {
        return accountDao.getAll();
    }


    //---------------------------------
    //  GET AND SET                   -
    //---------------------------------


    public Customer getCustomer() {
        return customer;
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

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
