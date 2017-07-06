package no.phasfjo.infrastructure.item.itemOne;

import no.phasfjo.dto.item.ItemOne;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.item.ItemOne.GET_ALL_ITEM_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaItemOneDao implements ItemOneDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaItemOneDao() {
    }

    public JpaItemOneDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ItemOne persist(ItemOne item) {
        if (item == null)
            throw new IllegalArgumentException("No item could be created!");
        entityManager.persist(item);
        return item;
    }

    @Override
    public ItemOne findById(int id) {
        if (id != 0) {
            return entityManager.find(ItemOne.class, id);
        } else
            throw new IllegalArgumentException("An error has occurred no item with id:" + id + " were found");
    }

    @Override
    public boolean update(ItemOne item) {
        if (item == null)
            throw new IllegalArgumentException(("No item could be updated"));
        if (!entityManager.contains(item)) {
            entityManager.merge(item);
        }
        return true;
    }

    @Override
    public List<ItemOne> getAll() {
        TypedQuery<ItemOne> query = entityManager.createNamedQuery(GET_ALL_ITEM_ONE, ItemOne.class);
        return query.getResultList();
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            ItemOne item = entityManager.find(ItemOne.class, id);
            entityManager.remove(item);
            return true;
        }
        throw new IllegalArgumentException(String.format("Item with id-nr:{%d] could not be deleted =C ", id));
    }
}
