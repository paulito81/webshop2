package no.phasfjo.constraint;


import no.phasfjo.dto.creditcard.CreditcardType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulhasfjord on 13.12.2016.
 */
public class CreditTypeValidator implements ConstraintValidator<CreditType, Enum> {

    private List<CreditcardType> list = new ArrayList<>();


    @Override
    public void initialize(CreditType creditType) {
        list.add(CreditcardType.AMERICAN_EXPRESS);
        list.add(CreditcardType.BANK_OF_AMERICA);
        list.add(CreditcardType.BARCLAYCARD_US);
        list.add(CreditcardType.BCG_GLOBAL);
        list.add(CreditcardType.CAPITOL_ONE);
        list.add(CreditcardType.CARTE_BLANCHE);
        list.add(CreditcardType.CHASE);
        list.add(CreditcardType.CITIBANK);
        list.add(CreditcardType.CRESCO_GOLD);
        list.add(CreditcardType.DINERS_CLUB);
        list.add(CreditcardType.DISCOVERY);
        list.add(CreditcardType.FLEXI_VISA);
        list.add(CreditcardType.IKANO);
        list.add(CreditcardType.INSTAPAYMENT);
        list.add(CreditcardType.JCB);
        list.add(CreditcardType.KOMPLETT);
        list.add(CreditcardType.KREDITT365);
        list.add(CreditcardType.MAESTRO);
        list.add(CreditcardType.MANCHESTER_UNITED_VISA);
        list.add(CreditcardType.MASTERCARD);
        list.add(CreditcardType.NORWEGIAN);
        list.add(CreditcardType.PNC);
        list.add(CreditcardType.REMEMBER);
        list.add(CreditcardType.SANTANDER);
        list.add(CreditcardType.SAS_EUROBONUS);
        list.add(CreditcardType.SHELL);
        list.add(CreditcardType.UNION_PAY);
        list.add(CreditcardType.USAA);
        list.add(CreditcardType.US_BANK);
        list.add(CreditcardType.VISA);
        list.add(CreditcardType.VISA_MASTERCARD);
        list.add(CreditcardType.WELLS_FARGO);
        list.add(CreditcardType.YA);
    }

    @Override
    public boolean isValid(Enum input, ConstraintValidatorContext constraintValidatorContext) {

        if (input == null || input.equals("")) {
            return false;
        }

        for (CreditcardType i : list) {
            if (input.equals(i)) {
                System.out.println(true);
                return true;
            }
        }
        System.err.println(false);
        return false;
    }
}
