package no.phasfjo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by paulhasfjord on 15.12.2016.
 */
public class EditorValidator implements ConstraintValidator<Editor, String> {

    private String editorPattern = "^[ _A-Za-z0-9-\\p{javaLetter}\\p{Blank}]{7,30}";

    @Override
    public void initialize(Editor editor) {
    }

    @Override
    public boolean isValid(String inputEditor, ConstraintValidatorContext constraintValidatorContext) {
        return !(inputEditor == null || !inputEditor.matches(editorPattern));
    }
}
