package no.phasfjo.infrastructure.order.order2;

import no.phasfjo.dto.order.Order2;
import no.phasfjo.dto.orderline.OrderLine2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static no.phasfjo.dto.order.Order2.GET_ALL_ORDER_TWO;
import static no.phasfjo.dto.orderline.OrderLine2.GET_ALL_DETACHED_ORDER_LINE_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaOrder2Dao implements Order2Dao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaOrder2Dao() {

    }

    public JpaOrder2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order2 persist(Order2 order) {
        if (order == null)
            throw new IllegalArgumentException("No order could not be created!");
        entityManager.persist(order);
        return order;

    }

    @Override
    public List<Order2> getAll() {
        TypedQuery<Order2> query = entityManager.createNamedQuery(GET_ALL_ORDER_TWO, Order2.class);
        return query.getResultList();
    }

    @Override
    public List<Order2> getDetachedOrder() {
        List<Order2> detachedOrders = new ArrayList<>();
        List<Order2> orders = getAll();

        for (Order2 order : orders) {
            TypedQuery<OrderLine2> query = entityManager.createNamedQuery(GET_ALL_DETACHED_ORDER_LINE_TWO, OrderLine2.class);
            query.setParameter("id", order.getId());
            List<OrderLine2> orderLines = query.getResultList();
            if (orderLines.isEmpty()) {
                detachedOrders.add(order);
            }
        }
        return detachedOrders;
    }

    @Override
    public boolean update(Order2 order) {
        if (order == null) {
            return false;
        }

        if (!entityManager.contains(order)) {
            entityManager.merge(order);
        }
        return true;
    }

    @Override
    public Order2 findById(int id) {
        if (id != 0) {
            return entityManager.find(Order2.class, id);
        } else
            throw new IllegalArgumentException("Error no order was found");
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            Order2 order = entityManager.find(Order2.class, id);
            entityManager.remove(order);
            return true;
        }
        throw new IllegalArgumentException(String.format("Order with id-nr:{%d] could not be deleted =C ", id));
    }

    @Override
    public List<Order2> calculateAllOrders() {
        TypedQuery<Order2> query = entityManager.createNamedQuery(GET_ALL_ORDER_TWO, Order2.class);
        return query.getResultList();
    }
}
