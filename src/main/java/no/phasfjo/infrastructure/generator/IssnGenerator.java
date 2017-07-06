package no.phasfjo.infrastructure.generator;

import no.phasfjo.infrastructure.logger.Loggable;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by phasf on 31.10.2016.
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Inject
   // @NumberOfDigits(value = Digits.EIGHT, odd = false)
    @EightDigits
    private String prefix;

    @Inject
    @Random
    private double postfix;

    @Loggable
    public String generateNumber() {
        return prefix + postfix;
        //     String ISSN = "8-" + Math.abs(new Random().nextInt());
     //   logger.info("Generated ISBN : " + ISSN);
       // return ISSN;
    }
}
