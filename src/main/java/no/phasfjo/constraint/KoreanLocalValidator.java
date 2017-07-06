package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 26.11.2016.
 */
public class KoreanLocalValidator implements ConstraintValidator<KoreanLocal, String> {

    private String koreanlocalPattern = "^9[0-9]{15}$";

    @Override
    public void initialize(KoreanLocal koreanLocal) {

    }

    @Override
    public boolean isValid(String koreanLocal, ConstraintValidatorContext constraintValidatorContext) {
        return !(koreanLocal == null || !koreanLocal.matches(koreanlocalPattern));
    }
}
