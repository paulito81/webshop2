package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
public class ImageValidator implements ConstraintValidator<Img, String>{


    /*
    File path must contain picture format.
     */
    private String picturePattern = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*.(jpg|jpeg|png|gif)$";

    @Override
    public void initialize(Img String) {

    }

    @Override
    public boolean isValid(String image, ConstraintValidatorContext constraintValidatorContext) {
        return !(image == null || !image.matches(picturePattern) );
    }
}
