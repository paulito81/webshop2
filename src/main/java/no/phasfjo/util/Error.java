package no.phasfjo.util;

import javax.validation.ConstraintViolation;
import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface Error {

    public List<ConstraintViolation> printErrorList();

}
