package no.phasfjo.dto.creditcard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public class CardTypes {
    public List<Card> list = Arrays.asList(Card.values());

    public void CardType() {

    }

    private enum Card {

        AMERICAN_EXPRESS("AMERICAN_EXPRESS"),
        BANK_OF_AMERICA("BANK_OF_AMERICA"),
        BARCLAYCARD_US("BARCLAYCARD_US"),
        CAPITOL_ONE("CAPITOL_ONE"),
        CITIBANK("CITIBANK"),
        CRESCO_GOLD("CRESCO_GOLD"),
        CHASE("CHASE"),
        DINERS_CLUB("DINERS_CLUB"),
        DISCOVER("DISCOVER"),
        FLEXI_VISA("FLEXI_VISA"),
        IKANO("IKANO"),
        JCB("JCB"),
        KOMPLETT("KOMPLETT"),
        KREDITT365("KREDITT365"),
        MANCHESTER_UNITED_VISA("MANCHESTER_UNITED_VISA"),
        MASTERCARD("MASTERCARD"),
        NORWEGIAN("NORWEGIAN"),
        PNC("PNC"),
        REMEMBER("REMEMBER"),
        SANTANDER("SANTANDER"),
        SAS_EUROBONUS("SAS_EUROBONUS"),
        SHELL("SHELL"),
        USAA("USAA"),
        US_BANK("US_BANK"),
        VISA("VISA"),
        YA("YA"),
        WELLS_FARGO("WELLS_FARGO");

        private String value;

        Card(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    public boolean list(String input) throws Exception {

        if (input == null) {
            System.out.println("No value!");
            return false;
        }
        for (Card c : list) {
            if (input.matches(c.toString())) {
                System.out.println(input + " - is valid!");
                return true;
            }
        }
        throw new IllegalArgumentException("Not a valid value " + input);
    }

}
