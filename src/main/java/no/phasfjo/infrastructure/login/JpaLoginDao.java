package no.phasfjo.infrastructure.login;

import no.phasfjo.dto.login.Login;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by paulhasfjord on 17.01.2017.
 */
@JpaLogin
@Stateless
public class JpaLoginDao implements LoginDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaLoginDao() {
    }

    JpaLoginDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Login persist(Login login) {
        if (login == null)
            throw new IllegalArgumentException("No customer could login in");
        entityManager.persist(login);
        return login;
    }

    @Override
    public Boolean update(Login login) {
        if (!entityManager.contains(login)) {
            entityManager.merge(login);
        }
        return true;
    }
}
