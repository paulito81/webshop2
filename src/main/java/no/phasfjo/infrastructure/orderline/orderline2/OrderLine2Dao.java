package no.phasfjo.infrastructure.orderline.orderline2;

import no.phasfjo.dto.orderline.OrderLine2;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface OrderLine2Dao {
    OrderLine2 persist(OrderLine2 orderLine);

    List<OrderLine2> getAll();

    boolean update(OrderLine2 orderLine);

    OrderLine2 findById(int id);

    boolean delete(int id);

    List<OrderLine2> calculateAllOrders();
}
