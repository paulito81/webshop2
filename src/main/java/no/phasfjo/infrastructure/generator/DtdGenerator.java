package no.phasfjo.infrastructure.generator;

import javax.inject.Inject;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by phasf on 03.11.2016.
 */

//@NumberOfDigits(value = Digits.THIRTEEN, odd = false)
@ThirteenDigits
public class DtdGenerator implements DateGenerator {

    @Inject
    private Logger logger;

    public Date generateDate() {
        Date instance = new Date();
        logger.info("Generated InitDate : " + instance.toString());
        return instance;
    }
}
