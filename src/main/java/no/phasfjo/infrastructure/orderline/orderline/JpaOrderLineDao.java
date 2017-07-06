package no.phasfjo.infrastructure.orderline.orderline;

import no.phasfjo.dto.orderline.OrderLine;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.orderline.OrderLine.GET_ALL_ORDER_LINES;

/**
 * Created by phasf on 27.01.2017.
 */
public class JpaOrderLineDao implements OrderLineDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaOrderLineDao() {

    }

    public JpaOrderLineDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public OrderLine persist(OrderLine orderLine) {
        if (orderLine == null)
            throw new IllegalArgumentException("No orderline could not be created!");
        entityManager.persist(orderLine);

        return orderLine;
    }

    @Override
    public List<OrderLine> getAll() {
        TypedQuery<OrderLine> query = entityManager.createNamedQuery(GET_ALL_ORDER_LINES, OrderLine.class);
        return query.getResultList();
    }

    @Override
    public boolean update(OrderLine orderLine) {

        if (orderLine == null) {
            return false;
        }

        if (!entityManager.contains(orderLine)) {
            entityManager.merge(orderLine);
        }

        return true;
    }

    @Override
    public OrderLine findById(int id) {
        if (id != 0) {
            return entityManager.find(OrderLine.class, id);
        } else {
            throw new IllegalArgumentException("Error no orderline was found");
        }
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            OrderLine order = entityManager.find(OrderLine.class, id);
            entityManager.remove(order);
            return true;
        }
        throw new IllegalArgumentException(String.format("Orderline with id-nr:{%d] could not be deleted =C ", id));
    }
}
