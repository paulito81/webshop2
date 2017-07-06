package no.phasfjo.infrastructure.orderline.orderline2;

import no.phasfjo.dto.orderline.OrderLine2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.orderline.OrderLine2.GET_ALL_ORDERLINES_TWO;
import static no.phasfjo.dto.orderline.OrderLine2.GET_TOTAL_SUM_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaOrderLine2Dao implements OrderLine2Dao{

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaOrderLine2Dao() {

    }

    public JpaOrderLine2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public OrderLine2 persist(OrderLine2 orderLine) {
        if (orderLine == null)
            throw new IllegalArgumentException("No orderline could not be created!");
        entityManager.persist(orderLine);

        return orderLine;
    }

    @Override
    public List<OrderLine2> getAll() {
        TypedQuery<OrderLine2> query = entityManager.createNamedQuery(GET_ALL_ORDERLINES_TWO, OrderLine2.class);
        return query.getResultList();
    }

    @Override
    public boolean update(OrderLine2 orderLine) {

        if (orderLine == null) {
            return false;
        }
        if (!entityManager.contains(orderLine)) {
            entityManager.merge(orderLine);
        }
        return true;
    }

    @Override
    public OrderLine2 findById(int id) {
        if (id != 0) {
            return entityManager.find(OrderLine2.class, id);
        } else {
            throw new IllegalArgumentException("Error no orderline was found");
        }
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            OrderLine2 order = entityManager.find(OrderLine2.class, id);
            entityManager.remove(order);
            return true;
        }
        throw new IllegalArgumentException(String.format("Orderline with id-nr:{%d] could not be deleted =C ", id));
    }

    @Override
    public List<OrderLine2> calculateAllOrders() {
        TypedQuery<OrderLine2> query = entityManager.createNamedQuery(GET_TOTAL_SUM_TWO, OrderLine2.class);
        return query.getResultList();

    }
}
