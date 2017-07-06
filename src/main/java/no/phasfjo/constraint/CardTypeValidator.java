package no.phasfjo.constraint;

import no.phasfjo.dto.creditcard.CardTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 27.11.2016.
 */
public class CardTypeValidator implements ConstraintValidator<CardType, String> {

    private CardTypes cardTypes = new CardTypes();

    @Override
    public void initialize(CardType cardType) {
    }

    @Override
    public boolean isValid(String cardtype, ConstraintValidatorContext constraintValidatorContext) {
        try {

            if (cardTypes.list(cardtype)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
