package arora.saurabh;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class LiveDashboardTest {

    private Orders orders;

    @Before
    public void init() {
        orders = new Orders();
    }

    @Test
    public void sellOrdersOfSamePriceShouldBeMergedAndDisplayedInAscendingOrderOfPrice() {
        orders.register(new Order("user1", 3.5, 306, OrderType.SELL),
                        new Order("user2", 1.2, 310, OrderType.SELL),
                        new Order("user3", 1.5, 307, OrderType.SELL),
                        new Order("user4", 2.0, 306, OrderType.SELL));

        assertThat(new LiveDashboard(orders).displaySellOrders()).containsSequence(new LiveOrder(5.5, 306, OrderType.SELL),
                                                                                  new LiveOrder(1.5, 307, OrderType.SELL),
                                                                                  new LiveOrder(1.2, 310, OrderType.SELL));
    }

    @Test
    public void buyOrdersOfSamePriceShouldBeMergedAndDisplayedInDescendingOrderOfPrice() {
        orders.register(new Order("user1", 3.5, 306, OrderType.BUY),
                new Order("user2", 1.2, 310, OrderType.BUY),
                new Order("user3", 1.5, 307, OrderType.BUY),
                new Order("user4", 2.0, 306, OrderType.BUY));

        assertThat(new LiveDashboard(orders).displayBuyOrders()).containsSequence(new LiveOrder(1.2, 310, OrderType.BUY),
                                                                                   new LiveOrder(1.5, 307, OrderType.BUY),
                                                                                   new LiveOrder(5.5, 306, OrderType.BUY));
    }

    @Test
    public void cancelledOrdersShouldNotBeDisplayed() {
        orders.register(new Order("user1", 3.5, 306, OrderType.BUY),
                        new Order("user2", 1.2, 310, OrderType.BUY),
                        new Order("user3", 1.5, 307, OrderType.BUY),
                        new Order("user4", 2.0, 306, OrderType.BUY));

        orders.cancel(new Order("user4", 2.0, 306, OrderType.BUY));

        assertThat(new LiveDashboard(orders).displayBuyOrders()).containsSequence(new LiveOrder(1.2, 310, OrderType.BUY),
                                                                                  new LiveOrder(1.5, 307, OrderType.BUY),
                                                                                  new LiveOrder(3.5, 306, OrderType.BUY));
    }

    @Test
    public void liveDashboardShouldDisplayNothingWhenAllOrdersHaveBeenCancelled() {
        orders.register(new Order("user1", 3.5, 306, OrderType.BUY),
                        new Order("user2", 1.2, 310, OrderType.BUY),
                        new Order("user4", 2.0, 306, OrderType.BUY));

        orders.cancel(new Order("user4", 2.0, 306, OrderType.BUY),
                      new Order("user1", 3.5, 306, OrderType.BUY),
                      new Order("user2", 1.2, 310, OrderType.BUY));

        assertThat(new LiveDashboard(orders).displayBuyOrders()).isEmpty();
    }

    @Test
    public void liveDashboardShouldDisplayBuyAndSellOrdersSeparately() {
        orders.register(new Order("user1", 3.5, 306, OrderType.BUY),
                        new Order("user2", 1.2, 310, OrderType.BUY),
                        new Order("user3", 1.5, 307, OrderType.BUY),
                        new Order("user1", 1.5, 506, OrderType.SELL),
                        new Order("user2", 1.2, 510, OrderType.SELL),
                        new Order("user3", 0.7, 507, OrderType.SELL));

        LiveDashboard liveDashboard = new LiveDashboard(orders);

        assertThat(liveDashboard.displayBuyOrders()).containsSequence(new LiveOrder(1.2, 310, OrderType.BUY),
                                                                      new LiveOrder(1.5, 307, OrderType.BUY),
                                                                      new LiveOrder(3.5, 306, OrderType.BUY));

        assertThat(liveDashboard.displaySellOrders()).containsSequence(new LiveOrder(1.5, 506, OrderType.SELL),
                                                                       new LiveOrder(0.7, 507, OrderType.SELL),
                                                                       new LiveOrder(1.2, 510, OrderType.SELL));
    }
}
