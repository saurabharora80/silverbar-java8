package arora.saurabh;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LiveDashboard {
    private final Orders orders;

    public LiveDashboard(Orders orders) {
        this.orders = orders;
    }

    public List<LiveOrder> displaySellOrders() {
        return displayOrders(OrderType.SELL, Comparator.comparing(LiveOrder::getPricePerKgInPounds));
    }

    public List<LiveOrder> displayBuyOrders() {
        return displayOrders(OrderType.BUY, (o1, o2) -> o2.getPricePerKgInPounds().compareTo(o1.getPricePerKgInPounds()));
    }

    private List<LiveOrder> displayOrders(OrderType orderType, Comparator<LiveOrder> orderComparator) {
        return orders
                .getOrdersOfType(orderType).stream()
                .map(Order::liveOrder)
                .collect(Collectors.groupingBy(LiveOrder::getPricePerKgInPounds)).entrySet().stream()
                .map(LiveOrder.addUpTheQuantityOfLiveOrders)
                .sorted(orderComparator)
                .collect(Collectors.toList());
    }

}
