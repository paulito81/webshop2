package no.phasfjo.infrastructure.item.itemTwo;

import no.phasfjo.dto.item.ItemTwo;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface ItemTwoDao {

    ItemTwo persist(ItemTwo item);

    List<ItemTwo> getAll();

    ItemTwo findById(int id);

    boolean update(ItemTwo item);

    boolean delete(int id);
}
