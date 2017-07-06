package no.phasfjo.infrastructure.orderline.orderline;

import no.phasfjo.dto.orderline.OrderLine;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface OrderLineDao {
    OrderLine persist(OrderLine orderLine);

    List<OrderLine> getAll();

    boolean update(OrderLine orderLine);

    OrderLine findById(int id);

    boolean delete(int id);
}
