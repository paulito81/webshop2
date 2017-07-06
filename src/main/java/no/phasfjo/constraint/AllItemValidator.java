package no.phasfjo.constraint;


import no.phasfjo.dto.item.ItemType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulhasfjord on 17.12.2016.
 */
public class AllItemValidator implements ConstraintValidator<AllItemTypes, Enum> {

    private List<ItemType> list = new ArrayList<>();

    @Override
    public void initialize(AllItemTypes allItemTypes) {
        list.add(ItemType.BOOK);
        list.add(ItemType.CD);
        list.add(ItemType.FILM);
        list.add(ItemType.MACBOOK_PRO);
    }

    @Override
    public boolean isValid(Enum input, ConstraintValidatorContext constraintValidatorContext) {
        if (input == null || input.equals("")) {
            return false;
        }

        for (ItemType i : list) {
            if (input.equals(i)) {
                System.out.println("true");
                return true;
            }
        }
        return false;
    }
}
