package no.phasfjo.infrastructure.creditcard.creditcard2;

import no.phasfjo.dto.creditcard.CreditCard2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.creditcard.CreditCard2.GET_ALL_CREDITCARD_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
public class JpaCreditCard2Dao implements CreditCard2Dao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaCreditCard2Dao() {
    }

    public JpaCreditCard2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CreditCard2 persist(CreditCard2 creditCard) {
        if (creditCard == null)
            throw new IllegalArgumentException("No creditcard could be created!");
        entityManager.persist(creditCard);
        return creditCard;

    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            CreditCard2 creditCard2 = entityManager.find(CreditCard2.class, id);
            entityManager.remove(creditCard2);
            return true;
        }
        throw new IllegalArgumentException(String.format("Credit card with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public boolean update(CreditCard2 creditCard) {
        if (creditCard == null)
            throw new IllegalArgumentException(("No account were found"));
        if (!entityManager.contains(creditCard)) {
            entityManager.merge(creditCard);
        }
        return true;
    }

    @Override
    public List<CreditCard2> getAll() {
        TypedQuery<CreditCard2> query = entityManager.createNamedQuery(GET_ALL_CREDITCARD_TWO, CreditCard2.class);
        return query.getResultList();

    }

    @Override
    public CreditCard2 findById(int id) {
        if (id != 0) {
            return entityManager.find(CreditCard2.class, id);
        } else
            throw new IllegalArgumentException("An error has occurred no creditcard was found");
    }
}
