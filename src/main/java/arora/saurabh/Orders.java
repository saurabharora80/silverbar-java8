package arora.saurabh;

import java.util.*;
import java.util.stream.Collectors;

class Orders {

    private List<Order> orders = new ArrayList<>();

    void register(Order... orders) {
        this.orders.addAll(Arrays.asList(orders));
    }

    List<Order> getOrdersOfType(OrderType type) {
        return Collections.unmodifiableList(orders.stream().filter((order) -> order.isOfType(type)).collect(Collectors.toList()));
    }

    void cancel(Order... orders) {
        this.orders.removeAll(Arrays.asList(orders));
    }

}
