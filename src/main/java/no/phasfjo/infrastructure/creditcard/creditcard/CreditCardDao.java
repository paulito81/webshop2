package no.phasfjo.infrastructure.creditcard.creditcard;

import no.phasfjo.dto.creditcard.CreditCard;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface CreditCardDao {

    CreditCard persist(CreditCard creditCard);

    boolean delete(int id);

    boolean update(CreditCard creditCard);

    List<CreditCard> getAll();

    CreditCard findById(int id);
}
