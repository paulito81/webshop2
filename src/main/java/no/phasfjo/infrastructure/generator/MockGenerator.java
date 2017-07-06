package no.phasfjo.infrastructure.generator;

import no.phasfjo.infrastructure.logger.Loggable;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by phasf on 31.10.2016.
 */
@Alternative
//@NumberOfDigits(value = Digits.THIRTEEN, odd = true)
@ThirteenDigits
public class MockGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Loggable
    public String generateNumber() {
        String mock = "MOCK-" + Math.abs(new Random().nextInt());
        logger.info("Generated Mock : " + mock);
        return mock;
    }

    @Loggable
    public String generateDate(){
        Date date = new Date();
        long time = date.getTime();
        String dateTime = "InitDate" + Long.toString(time);
        logger.info("Generated date : " + Long.toString(time));
        return dateTime;
    }
}
