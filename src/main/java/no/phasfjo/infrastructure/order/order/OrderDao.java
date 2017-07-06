package no.phasfjo.infrastructure.order.order;

import no.phasfjo.dto.order.Order;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface OrderDao {
    Order persist(Order order);

    List<Order> getAll();

    List<Order> getDetachedOrder();

    boolean update(Order order);

    Order findById(int id);

    boolean delete(int id);
}
