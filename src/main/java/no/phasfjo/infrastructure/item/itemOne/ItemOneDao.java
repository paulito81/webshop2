package no.phasfjo.infrastructure.item.itemOne;

import no.phasfjo.dto.item.ItemOne;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface ItemOneDao {


    ItemOne persist(ItemOne item);

    List<ItemOne> getAll();

    ItemOne findById(int id);

    boolean update(ItemOne item);

    boolean delete(int id);
}
