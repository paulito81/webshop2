package no.phasfjo.infrastructure.order.order;

import no.phasfjo.dto.order.Order;
import no.phasfjo.dto.orderline.OrderLine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static no.phasfjo.dto.order.Order.GET_ALL_ORDER_ONE;
import static no.phasfjo.dto.orderline.OrderLine.GET_ALL_DETACHED_ORDER_LINE_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaOrderDao implements OrderDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaOrderDao() {

    }

    public JpaOrderDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order persist(Order order) {
        if (order == null)
            throw new IllegalArgumentException("No order could not be created!");
        entityManager.persist(order);
        return order;
    }

    @Override
    public List<Order> getAll() {
        TypedQuery<Order> query = entityManager.createNamedQuery(GET_ALL_ORDER_ONE, Order.class);
        return query.getResultList();
    }

    @Override
    public boolean update(Order order) {
        if (order == null) {
            return false;
        }

        if (entityManager.contains(order)) {
            entityManager.merge(order);
        }
        return true;
    }

    @Override
    public Order findById(int id) {
        if (id != 0) {
            return entityManager.find(Order.class, id);
        } else
            throw new IllegalArgumentException("Error no order was found");

    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            Order order = entityManager.find(Order.class, id);
            entityManager.remove(order);
            return true;
        }
        throw new IllegalArgumentException(String.format("Order with id-nr:{%d] could not be deleted =C ", id));
    }

    public List<Order> getDetachedOrder() {
        List<Order> detachedOrders = new ArrayList<>();
        List<Order> orders = getAll();

        for (Order order : orders) {
            TypedQuery<OrderLine> query = entityManager.createNamedQuery(GET_ALL_DETACHED_ORDER_LINE_ONE, OrderLine.class);
            query.setParameter("id", order.getId());
            List<OrderLine> orderLines = query.getResultList();
            if (orderLines.isEmpty()) {
                detachedOrders.add(order);
            }
        }
        return detachedOrders;
    }
}
