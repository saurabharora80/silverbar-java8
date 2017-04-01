package arora.saurabh;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersTest {
    private Orders orders;

    @Before
    public void init() {
        orders = new Orders();
    }

    @Test
    public void registerAOrder() {
        Order order = new Order("user1", 3.5, 306, OrderType.BUY);

        orders.register(order);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(order);
    }

    @Test
    public void registerMultipleOrders() {
        Order orderOne = new Order("user1", 3.5, 306, OrderType.BUY);
        Order orderTwo = new Order("user2", 1.5, 310, OrderType.BUY);

        orders.register(orderOne, orderTwo);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(orderOne, orderTwo);
    }

    @Test
    public void cancelAOrder() {
        Order orderOne = new Order("user1", 3.5, 306, OrderType.BUY);
        Order orderTwo = new Order("user2", 1.5, 310, OrderType.BUY);

        orders.register(orderOne, orderTwo);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(orderOne, orderTwo);

        orders.cancel(orderTwo);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(orderOne);
    }

    @Test
    public void cancelMultipleOrders() {
        Order orderOne = new Order("user1", 3.5, 306, OrderType.BUY);
        Order orderTwo = new Order("user2", 1.5, 310, OrderType.BUY);

        orders.register(orderOne, orderTwo);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(orderOne, orderTwo);

        orders.cancel(orderTwo, orderOne);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).isEmpty();
    }

    @Test
    public void getAllBuyOrders() {
        Order orderOne = new Order("user1", 3.5, 306, OrderType.BUY);
        Order orderTwo = new Order("user2", 1.5, 310, OrderType.SELL);
        Order orderThree = new Order("user3", 2.5, 300, OrderType.BUY);

        orders.register(orderOne, orderTwo, orderThree);

        assertThat(orders.getOrdersOfType(OrderType.BUY)).containsExactly(orderOne, orderThree);
    }

    @Test
    public void getAllSellOrders() {
        Order orderOne = new Order("user1", 3.5, 306, OrderType.SELL);
        Order orderTwo = new Order("user2", 1.5, 310, OrderType.SELL);
        Order orderThree = new Order("user3", 2.5, 300, OrderType.BUY);

        orders.register(orderOne, orderTwo, orderThree);

        assertThat(orders.getOrdersOfType(OrderType.SELL)).containsExactly(orderOne, orderTwo);
    }

}
