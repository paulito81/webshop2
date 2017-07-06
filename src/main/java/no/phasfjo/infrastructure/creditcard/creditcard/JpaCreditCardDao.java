package no.phasfjo.infrastructure.creditcard.creditcard;

import no.phasfjo.dto.creditcard.CreditCard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.creditcard.CreditCard.GET_ALL_CREDITCARD_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
public class JpaCreditCardDao implements CreditCardDao{

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaCreditCardDao(){

    }
    public JpaCreditCardDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public CreditCard persist(CreditCard creditCard) {
        if (creditCard == null)
            throw new IllegalArgumentException("No creditcard could be created!");
        entityManager.persist(creditCard);
        return creditCard;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            CreditCard creditCard = entityManager.find(CreditCard.class, id);
            entityManager.remove(creditCard);
            return true;
        }
        throw new IllegalArgumentException(String.format("Credit card with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public boolean update(CreditCard creditCard) {
        if (creditCard == null)
            throw new IllegalArgumentException(("No account were found"));
        if (!entityManager.contains(creditCard)) {
            entityManager.merge(creditCard);
        }
        return true;
    }

    @Override
    public List<CreditCard> getAll() {
        TypedQuery<CreditCard> query = entityManager.createNamedQuery(GET_ALL_CREDITCARD_ONE, CreditCard.class);
        return query.getResultList();
    }

    @Override
    public CreditCard findById(int id) {
        if (id != 0) {
            return entityManager.find(CreditCard.class, id);
        } else
            throw new IllegalArgumentException("An error has occurred no creditcard was found");
    }
}
