package no.phasfjo.infrastructure.order.order2;

import no.phasfjo.dto.order.Order2;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface Order2Dao {
    Order2 persist(Order2 order);

    List<Order2> getAll();

    List<Order2> getDetachedOrder();

    boolean update(Order2 order);

    Order2 findById(int id);

    boolean delete(int id);

    List<Order2> calculateAllOrders();
}
