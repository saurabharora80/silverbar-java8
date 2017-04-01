package arora.saurabh;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class LiveOrderTest {

    @Test
    public void addUpTheQuantityOfLiveOrders() {
        LiveOrder reducedLiveOrder = LiveOrder.addUpTheQuantityOfLiveOrders.apply(
                new AbstractMap.SimpleEntry<>(306, Arrays.asList(new LiveOrder(1.2, 306, OrderType.SELL),
                                                                 new LiveOrder(3.8, 306, OrderType.SELL),
                                                                 new LiveOrder(1.5, 306, OrderType.SELL))));

        assertThat(reducedLiveOrder).isEqualTo(new LiveOrder(6.5, 306, OrderType.SELL));
    }

    @Test
    public void returnTheSameLiveOrderWhenThereIsOnlyOneOrderInGroup() {
        LiveOrder reducedLiveOrder = LiveOrder.addUpTheQuantityOfLiveOrders.apply(
                new AbstractMap.SimpleEntry<>(306, Collections.singletonList(new LiveOrder(1.2, 306, OrderType.SELL))));

        assertThat(reducedLiveOrder).isEqualTo(new LiveOrder(1.2, 306, OrderType.SELL));
    }
}