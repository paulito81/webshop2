package no.phasfjo.infrastructure.order.order;

import no.phasfjo.dto.order.Order;
import no.phasfjo.dto.orderline.OrderLine;
import no.phasfjo.infrastructure.orderline.orderline.JpaOrderLineDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by phasf on 27.01.2017.
 */
public class OrderIT {
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaOrderLineDao jpaOrderLineDao;
    private JpaOrderDao jpaOrderDao;

    /*
    TODO
    TESTS WILL CRASH IN THE FUTURE DUE TO DATE ISSUES
     */

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaOrderDao = new JpaOrderDao(entityManager);
        jpaOrderLineDao = new JpaOrderLineDao(entityManager);

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
    private String timeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.getTimeZone().getDisplayName();
    }

    @Test
    public void persistOneNewOrderTest() throws Exception {

        Order order = new Order();
        Date date = new Date();
        order.setQuantity(1);
        order.setCreationDate(date);
        order.setPaymentDate(new Date("12/10/2019 22:00:20 GMT"));
        order.setDeliveryDate(new Date("12/12/2019 20:00:10 GMT"));
        order.setTotalAmount(10d);

        OrderLine oL = new OrderLine("CD med Jackson", 10d, 1);
        OrderLine oL2 = new OrderLine("The white man", 24d, 2);
        List<OrderLine> orderLines = new ArrayList<>();

        orderLines.add(oL);
        orderLines.add(oL2);
        order.setOrderLines(orderLines);

        entityManager.getTransaction().begin();
        jpaOrderDao.persist(order);
        jpaOrderLineDao.persist(oL);
        jpaOrderLineDao.persist(oL2);
        entityManager.getTransaction().commit();

        assertNotNull("OrderOld ID should not be null", order.getId());
        assertNotNull("Orderline1 ID should not be null", oL.getId());
        assertNotNull("Orderline2 ID should not be null", oL2.getId());

        order = entityManager.find(Order.class, order.getId());
        assertNotNull("Orderlines should not be null", order.getOrderLines());
        assertNotNull("Orderlines should have 2 order lines", order.getOrderLines().size());

    }

    @Test
    public void createTwoOrdersAndGetAllOrderTest() throws Exception {

        //ORDERLINES
        List<OrderLine> orderLines = new ArrayList<>();

        //ONE
        Date date = new Date(); //NB! TIMESTAMP WILL CRASH TEST IN FUTURE

        Date payment = new Date("12/10/2019 22:00:20");
        Integer quantity = 1;
        Date delivery = new Date("12/12/2020 20:00:10");
        Double amount = 10d;

        OrderLine orderLine = new OrderLine("Stol", 100d, 1);
        OrderLine orderLine3 = new OrderLine("The matrix", 10d, 1);
        OrderLine orderLine4 = new OrderLine("Iphone s", 10000d, 1);

        //ONE_ORDER_LINES
        orderLines.add(orderLine);
        orderLines.add(orderLine3);
        orderLines.add(orderLine4);

        Order order = new Order(date, amount, payment, delivery, quantity, orderLines);
        //FREE
        orderLines = new ArrayList<>(); //FREE ORDER ONE

        //TWO
        Date create2 = new Date();        //NB! TIMESTAMP WILL CRASH TEST IN FUTURE

        Date payment2 = new Date("12/11/2019 22:00:20 GMT");
        Integer quantity2 = 2;
        Date delivery2 = new Date("12/14/2020 20:00:10 GMT");
        Double amount2 = 100d;

        OrderLine orderLine5 = new OrderLine("Bord", 10d, 2);
        OrderLine orderLine6 = new OrderLine("Simkort", 5d, 10);
        OrderLine orderLine7 = new OrderLine("Stasjonær PC", 100000d, 5);

        //TWO_ORDER_LINES
        orderLines.add(orderLine5);
        orderLines.add(orderLine6);
        orderLines.add(orderLine7);

        Order order2 = new Order(create2, amount2, payment2, delivery2, quantity2, orderLines);

        //PERSIST ONE
        entityManager.getTransaction().begin();
        jpaOrderLineDao.persist(orderLine);
        jpaOrderLineDao.persist(orderLine3);
        jpaOrderLineDao.persist(orderLine4);
        Order one = jpaOrderDao.persist(order);
        entityManager.getTransaction().commit();


        //PERSIST TWO
        entityManager.getTransaction().begin();
        jpaOrderLineDao.persist(orderLine5);
        jpaOrderLineDao.persist(orderLine6);
        jpaOrderLineDao.persist(orderLine7);
        Order two = jpaOrderDao.persist(order2);
        entityManager.getTransaction().commit();

        //TEST
        assertNotNull(order.getOrderLines().isEmpty());
        assertNotNull(order2.getOrderLines().isEmpty());
        assertEquals(3, jpaOrderDao.getAll().size());
        assertEquals(one.getOrderLines().size(), two.getOrderLines().size());
        assertNotSame(one, two);

        switch (timeZone()) {
            case "Eastern Standard Time":
                assertEquals(one.getPaymentDate().toString(), "Tue Dec 11 04:00:20 EST 2018");
                assertNotEquals(two.getDeliveryDate().toString(), "Thu Dec 15 02:00:10 EST 2018");   // GMT time is + 1hour
                assertNotEquals(two.getCreationDate().toString(), "Sun Dec 05 02:36:16 EST 2018");  // Create a new time at start
                break;
            case "Central Eastern Time":
                assertEquals(one.getPaymentDate().toString(), "Mon Dec 10 23:00:20 CET 2018");
                assertNotEquals(two.getDeliveryDate().toString(), "Wed Dec 14 21:00:10 CET 2018");   // CET time is - 5hour
                assertNotEquals(two.getCreationDate().toString(), "Sat Dec 04 21:36:16 CET 2018");  // Create a new time at start
                break;
            case "Central Standard Time":
                assertEquals(one.getPaymentDate().toString(), "Mon Dec 10 22:00:20 CST 2018");
                assertNotEquals(two.getDeliveryDate().toString(), "Wed Dec 14 20:00:10 CST 2018");   // CST time is -6hour
                assertNotEquals(two.getCreationDate().toString(), "Sat Dec 04 20:36:16 CST 2018");  // Create a new time at start
                break;
            case "Central European Time":
                assertEquals(one.getPaymentDate().toString(), "Tue Dec 10 22:00:20 CET 2019");
                assertNotEquals(two.getDeliveryDate().toString(), "Wed Dec 14 20:00:10 CET 2018");   // CST time is -6hour
                assertNotEquals(two.getCreationDate().toString(), "Sat Dec 04 20:36:16 CET 2018");  // Create a new time at start
                break;

            default:
                throw new IllegalArgumentException("Not a timezone");
        }
        assertEquals(one.getQuantity().toString(), "1");
        assertEquals(two.getQuantity().toString(), "2");

        jpaOrderDao.getAll().forEach(System.out::println);

    }

    @Test
    public void createOrderAndUpdateOrderTest() throws Exception {

        //CREATE-SETUP
        Date date = new Date();
        OrderLine orderLine = new OrderLine("Ball", 10d, 1);
        List<OrderLine> line2s = new ArrayList<>();
        line2s.add(orderLine);

        Order order = new Order(date, 100d, new Date("12/4/2019 14:06:20 GMT"), new Date("12/6/2019 22:00:20 GMT"), 1, line2s);
        order.setOrderLines(line2s);

        entityManager.getTransaction().begin();
        jpaOrderDao.persist(order);
        jpaOrderLineDao.persist(orderLine);
        entityManager.getTransaction().commit();

        //UPDATE
        Order change = jpaOrderDao.findById(50);

        change.setTotalAmount(200d);
        change.setQuantity(2);
        change.setPaymentDate(new Date("12/09/2019 22:00:20 GMT"));
        change.setDeliveryDate(new Date("12/10/2019 10:00:09"));

        entityManager.getTransaction().begin();
        boolean updated = jpaOrderDao.update(change);
        entityManager.getTransaction().commit();

        //TEST
        assertTrue(updated);
        assertEquals(jpaOrderDao.findById(50).getQuantity().toString(), "2");
        assertTrue(jpaOrderDao.findById(50).getTotalAmount() == 200);
        assertNotSame(order.getDeliveryDate(), ("12/6/2019 22:00:20 GMT"));
        assertNotEquals(order.getPaymentDate(), ("12/4/2019 14:06:20 GMT"));
    }

    @Test
    public void createOrderAndFindOrderByIdTest() throws Exception {

        //CREATE-SETUP
        Date date = new Date();
        OrderLine orderLine = new OrderLine("Ball", 10d, 1);
        List<OrderLine> line2s = new ArrayList<>();
        line2s.add(orderLine);
        Order order = new Order(date, 100d, new Date("12/5/2019 14:06:20 GMT"), new Date("12/6/2019 22:00:20 GMT"), 1, line2s);

        order.setOrderLines(line2s);
        entityManager.getTransaction().begin();
        jpaOrderDao.persist(order);
        jpaOrderLineDao.persist(orderLine);
        entityManager.getTransaction().commit();

        //FIND BY ID
        Order result = jpaOrderDao.findById(order.getId());
        assertEquals(result.getId(), 50);
        assertTrue(result.getOrderLines().contains(orderLine));
        //TIMESTAMP IS SHIFTING
        assertNotEquals(jpaOrderDao.getAll(), "[Order{id=50, creationDate=Sun Dec 04 23:29:16 CET 2019, totalAmount=100.0, paymentDate=Tue Dec 05 15:06:20 CET 2019, deliveryDate=Wed Dec 06 23:00:20 CET 2019, quantity=1, orderLines=[OrderLine{id=101, item='Ball', unitPrice=10.0, quantity=1]}]");
        assertTrue(result.getId() > 1);
    }

    @Test
    public void createAndDeleteAOrderTest() throws Exception {

        //CREATE-SETUP
        Date date = new Date();
        OrderLine orderLine = new OrderLine("Ball", 10d, 1);
        List<OrderLine> line2s = new ArrayList<>();
        line2s.add(orderLine);
        Order order = new Order(date, 100d, new Date("12/4/2019 14:06:20 GMT"), new Date("12/6/2019 22:00:20 GMT"), 1, line2s);

        order.setOrderLines(line2s);
        entityManager.getTransaction().begin();
        jpaOrderDao.persist(order);
        jpaOrderLineDao.persist(orderLine);
        entityManager.getTransaction().commit();

        //DELETE
        Order findOrder = jpaOrderDao.findById(50);
        entityManager.getTransaction().begin();
        jpaOrderDao.delete(1);
        jpaOrderDao.delete(findOrder.getId());
        entityManager.getTransaction().commit();

        Order result = jpaOrderDao.findById(50);
        //TEST
        assertNull(result);
        assertEquals(jpaOrderDao.getAll().toString(), "[]");

    }

    @Test
    public void getDetachedOrders() throws Exception {
        List<Order> orders = jpaOrderDao.getDetachedOrder();
        Assert.assertTrue(orders.isEmpty());
    }
}
