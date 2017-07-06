package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 25.11.2016.
 */
public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {

    /*
       Must start with   65 contain  +13  digits between 4-9 and 0-9
       Must start with   65 contain  +13  digits between 4-9 and 0-9
       Must start with 6011 contain  +12  digits
       Must start with  622 contain  +12  digits between 6-9

       "^65[4-9][0-9]{13}|64[4-9][0-9]{13}|6011[0-9]{12}|(622(?:12[6-9]|1[3-9][0-9]|[2-8][0-9][0-9]|9[01][0-9]|92[0-5])[0-9]{10})$";
    */
    private String cardPattern = "^\\d{16}(?:[-\\s]\\d{4,})?$";

    @Override
    public void initialize(CardNumber cardNumber) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return !(number == null || number.equals("") || !number.matches(cardPattern));
    }
}
