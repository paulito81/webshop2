package no.phasfjo.infrastructure.order.order2;

import no.phasfjo.dto.order.Order2;
import no.phasfjo.dto.orderline.OrderLine2;
import no.phasfjo.infrastructure.orderline.orderline2.JpaOrderLine2Dao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public class Order2IT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaOrderLine2Dao jpaOrderLine2Dao;
    private JpaOrder2Dao jpaOrderDao;


    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaOrderDao = new JpaOrder2Dao(entityManager);
        jpaOrderLine2Dao = new JpaOrderLine2Dao(entityManager);

    }

    @After
    public void tearDown() throws Exception {
        if (entityManager != null) {
            entityManager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void persist() throws Exception {
        List<OrderLine2> orderLineS = new ArrayList<>();
        Order2 order = new Order2();
        order.setToDaysDate(new Date());
        order.setQuantity(1);


        OrderLine2 orderLine = new OrderLine2();
        orderLine.setQuantity(1);
        Date date = new Date();
        date.toInstant();
        orderLine.setCreationDate(date);
        orderLine.setPaymentDate(new Date("01/10/2020 20:00:00 GMT"));
        orderLine.setDeliveryDate(new Date("01/15/2020 20:00:00 GMT"));
        orderLine.setUnitPrice(15000.0);
        orderLine.setItem("Mac book Pro 2017");

        OrderLine2 orderLine2 = new OrderLine2();
        orderLine2.setQuantity(1);
        date.toInstant();
        orderLine2.setCreationDate(date);
        orderLine2.setPaymentDate(new Date("01/10/2020 19:00:00 GMT"));
        orderLine2.setDeliveryDate(new Date("01/15/2020 10:00:00 GMT"));
        orderLine2.setUnitPrice(15000.0);
        orderLine2.setItem("Mac book Pro 2017");

        orderLineS.add(orderLine);
        orderLineS.add(orderLine2);
        order.setOrderLines(orderLineS);

        order.setTotalAmount(orderLine.getUnitPrice() + orderLine2.getUnitPrice());

        entityManager.getTransaction().begin();
        jpaOrderLine2Dao.persist(orderLine);
        jpaOrderLine2Dao.persist(orderLine2);
        jpaOrderDao.persist(order);
        entityManager.getTransaction().commit();

        List<Order2> orderList = jpaOrderDao.calculateAllOrders();
        List<OrderLine2> orderLineList = jpaOrderLine2Dao.getAll();

        Assert.assertEquals(1, jpaOrderDao.getAll().size());
        Assert.assertTrue(!orderList.isEmpty());
        Assert.assertEquals("30000.0", orderList.iterator().next().getTotalAmount().toString());
        Assert.assertEquals(2, orderList.iterator().next().getOrderLines().size());
        Assert.assertEquals(orderList.iterator().next().getOrderLines().iterator().next().getItem(), orderLineList.iterator().next().getItem());
    }
}
