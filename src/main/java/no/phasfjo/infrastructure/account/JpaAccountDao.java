package no.phasfjo.infrastructure.account;

import no.phasfjo.dto.account.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.account.Account.GET_ALL_ACCOUNTS;

/**
 * Created by paulhasfjord on 17.01.2017.
 */

@Stateless @JpaAccount
public class JpaAccountDao implements AccountDao{

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaAccountDao() {
    }

    public JpaAccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Account persist(Account account) {
        if (account == null)
            throw new IllegalArgumentException("No account could be created!");
        entityManager.persist(account);
        return account;
    }

    @Override
    public Boolean delete(int id) {
        if (id != 0) {
            Account account = entityManager.find(Account.class, id);
            entityManager.remove(account);
            return true;
        }
        throw new IllegalArgumentException(String.format("Account with id-nr:{%d] could not be deleted =C ", id));
    }

    @Override
    public Account findById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("No id was found!");
        return entityManager.find(Account.class, id);
    }

    @Override
    public boolean update(Account account) {
        if (account == null)
            throw new IllegalArgumentException(("No account were found"));
        if (!entityManager.contains(account)) {
            entityManager.merge(account);
        }
        return true;
    }

    @Override
    public List<Account> getAll() {
        TypedQuery<Account> query = entityManager.createNamedQuery(GET_ALL_ACCOUNTS, Account.class);
        return query.getResultList();
    }

}
