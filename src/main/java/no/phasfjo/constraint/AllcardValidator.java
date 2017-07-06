package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 10.12.2016.
 */

public class AllcardValidator implements ConstraintValidator<AllCardTypes, String> {

    //---------------------------------------------------
    // Must start with 34 or 37 and have 15 digits      |
    //---------------------------------------------------
    private final String AMEX_PATTERN = "^3[47][0-9]{13}$";

    //---------------------------------------------------
    //Must start with 6541, 6556 and contain +12 digits  |
    //---------------------------------------------------
    private final String BCG_GLOBAL = "^(6541|6556)[0-9]{12}$";

    //------------------------------------------
    // Must start with 389 and have +11 digits |
    //------------------------------------------
    private final String CARTE_BLANCHE = "^389[0-9]{11}$";

    //---------------------------------------------------------------------
    // Must start with 300-305 , 36 or 38 and 14 digits                   |
    // (join venture between diners and mastercard start 5 and 16 digits) |
    //---------------------------------------------------------------------
    private final String DINERS_CLUB = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";

    //---------------------------------------------------------------------
    //Must start with 6011 or 65 and 16 digits                            |
    //---------------------------------------------------------------------
    private final String DISCOVERY = "^6(?:011|5[0-9]{2})[0-9]{12}$";

    // MISSING PATTERN
    private final String INSTA_PAYMENT = "^63[7-9][0-9]{13}$";

    //-------------------------------------------------------------------------------
    // Must start with 2131 or 1800 and 15 digits. Card start with 35 has 16 digits \
    //-------------------------------------------------------------------------------
    private final String JCB = "^(?:2131|1800|35\\d{3})\\d{11}$";

    // MISSING PATTERN
    private final String KOREAN_LOCAL = "^9[0-9]{15}$";

    //-------------------------------------------------------------------------------
    //    Must start with 6304, 6706, 6771 and contain +12 - 15 digits              |
    //-------------------------------------------------------------------------------
    private final String LASER = "^(6304|6706|6709|6771)[0-9]{12,15}$";

    //------------------------------------------------------------------------------------
    //Must start with 5018, 5020, 5038,6304, 6759,6761,6763 and contain + 8 - 15 digits  |
    //------------------------------------------------------------------------------------
    private final String MAESTRO = "^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$";


    //------------------------------------------------------------------------------------
    // Must start with 5 and contain 14 digits                                          \
    //------------------------------------------------------------------------------------
    private final String MASTERCARD_OLD = "^5[1-5][0-9]{14}$";

    //------------------------------------------------------------------------------------
    //Must start with 62 and have 14 - 17 digits
    //------------------------------------------------------------------------------------
    private final String UNION_PAY = "^(62[0-9]{14,17})$";


    //------------------------------------------------------------------------------------
    // Must start with 4 and has 13 digits. (old paymentCard 13 digits)                         |
    //------------------------------------------------------------------------------------
    private final String VISA = "^4[0-9]{12}(?:[0-9]{3})?$";

    //------------------------------------------------------------------------------------
    //    Must start with 51-55 or 2221-2270 and has 16 digits                           \
    //------------------------------------------------------------------------------------
    private final String VISA_MASTERCARD = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";


    @Override
    public void initialize(AllCardTypes allCardTypes) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null || s.equals("")) {
            return false;
        }

        if (s.matches(AMEX_PATTERN)) {
            System.out.println("American Express");
            return true;
        }
        if (s.matches(BCG_GLOBAL)) {
            System.out.println("BCGlobal");
            return true;
        }
        if (s.matches(CARTE_BLANCHE)) {
            System.out.println("CarterBlanche");
            return true;
        }
        if (s.matches(DINERS_CLUB)) {
            System.out.println("Diners");
            return true;
        }
        if (s.matches(DISCOVERY)) {
            System.out.println("Discovery");
            return true;
        }
        if (s.matches(INSTA_PAYMENT)) {
            System.out.println("Instapayment");
            return true;
        }
        if (s.matches(JCB)) {
            System.out.println("JCB");
            return true;
        }
        if (s.matches(KOREAN_LOCAL)) {
            System.out.println("Korean local");
            return true;
        }
        if (s.matches(LASER)) {
            System.out.println("Laser");
            return true;
        }
        if (s.matches(MAESTRO)) {
            System.out.println("Maestro");
            return true;
        }
        if (s.matches(MASTERCARD_OLD)) {
            System.out.println("Mastercard");
            return true;
        }
        if (s.matches(UNION_PAY)) {
            System.out.println("Union Pay");
            return true;
        }
        if (s.matches(VISA)) {
            System.out.println("Visacard");
            return true;
        }
        if (s.matches(VISA_MASTERCARD)) {
            System.out.println("Visa master");
            return true;
        }
        return false;
    }
}
