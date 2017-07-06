package no.phasfjo.infrastructure.generator;

import javax.enterprise.inject.Produces;

/**
 * Created by phasf on 06.11.2016.
 */
public class NumberProducer16 {

    @Produces
   @ThirteenDigits
  //  @NumberOfDigits(value = Digits.THIRTEEN, odd = false)
    private String prefixThirteenDigits = "13-";

    @Produces
    @ThirteenDigits
 //   @NumberOfDigits(value = Digits.THIRTEEN, odd = false)
    private int editorNumber = 84356;

    @Produces
    @EightDigits
    //@NumberOfDigits(value = Digits.EIGHT, odd = false)
    private String prefixEightDigits = "8-";

    @Produces
    @Random
    public double random() {
        return Math.abs(new java.util.Random().nextInt());
    }
}
