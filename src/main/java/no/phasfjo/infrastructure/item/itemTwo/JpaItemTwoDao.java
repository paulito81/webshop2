package no.phasfjo.infrastructure.item.itemTwo;

import no.phasfjo.dto.item.ItemTwo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.item.ItemTwo.GET_ALL_ITEM_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaItemTwoDao implements ItemTwoDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaItemTwoDao() {
    }

    public JpaItemTwoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ItemTwo persist(ItemTwo item) {
        if (item == null)
            throw new IllegalArgumentException("No item could be created!");
        entityManager.persist(item);
        return item;
    }

    @Override
    public ItemTwo findById(int id) {
        if (id != 0) {
            return entityManager.find(ItemTwo.class, id);
        } else
            throw new IllegalArgumentException("An error has occurred no item with id:" + id + " were found");

    }

    @Override
    public boolean update(ItemTwo item) {
        if (item == null)
            throw new IllegalArgumentException(("No item could be updated"));
        if (!entityManager.contains(item)) {
            entityManager.merge(item);
        }
        return true;
    }

    @Override
    public List<ItemTwo> getAll() {
        TypedQuery<ItemTwo> query = entityManager.createNamedQuery(GET_ALL_ITEM_TWO, ItemTwo.class);
        return query.getResultList();
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            ItemTwo item = entityManager.find(ItemTwo.class, id);
            entityManager.remove(item);
            return true;
        }
        throw new IllegalArgumentException(String.format("ItemOne with id-nr:{%d] could not be deleted =C ", id));

    }
}
