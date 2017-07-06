package no.phasfjo.infrastructure.creditcard.creditcard2;

import no.phasfjo.dto.creditcard.CreditCard2;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface CreditCard2Dao {

    CreditCard2 persist(CreditCard2 creditCard);

    boolean delete(int id);

    boolean update(CreditCard2 creditCard);

    List<CreditCard2> getAll();

    CreditCard2 findById(int id);

}
