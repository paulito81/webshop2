package no.phasfjo.infrastructure.generator;

import no.phasfjo.infrastructure.logger.Loggable;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by phasf on 31.10.2016.
 */

//@NumberOfDigits(value = Digits.THIRTEEN, odd = false)
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Inject
    @ThirteenDigits
    //@NumberOfDigits(value = Digits.THIRTEEN, odd = false)
    private String prefix;

    @Inject
    @ThirteenDigits
   // @NumberOfDigits(value = Digits.THIRTEEN, odd = false)
    private int editorNumber;

    @Inject
    @Random
    private double postfix;

    @Loggable
    public String generateNumber() {
        logger.warning("Debug message with @Inject");
        return prefix + editorNumber + postfix;
        //  String ISBN = "13-84356-" + Math.abs(new Random().nextInt());
       // logger.info("Generated ISBN : " + ISBN);
        //return ISBN;
    }
}
