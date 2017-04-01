package arora.saurabh;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LiveOrder {
    private final Double quantityInKgs;
    private final Integer pricePerKgInPounds;
    private final OrderType orderType;

    LiveOrder(Double quantityInKgs, Integer pricePerKgInPounds, OrderType orderType) {
        this.quantityInKgs = quantityInKgs;
        this.pricePerKgInPounds = pricePerKgInPounds;
        this.orderType = orderType;
    }

    Integer getPricePerKgInPounds() {
        return pricePerKgInPounds;
    }

    private LiveOrder addQuantity(LiveOrder otherLiveOrder) {
        return new LiveOrder(this.quantityInKgs + otherLiveOrder.quantityInKgs, this.pricePerKgInPounds, this.orderType);
    }

    /**
     * Given the usage of this function there would always be at least 1 LiveOrder in the Grouped Entry
     */
    static Function<Map.Entry<Integer,List<LiveOrder>>, LiveOrder> addUpTheQuantityOfLiveOrders =
            (groupedLiveOrders) -> groupedLiveOrders.getValue().stream().reduce(
                    new LiveOrder(0.0, groupedLiveOrders.getKey(), groupedLiveOrders.getValue().get(0).orderType), LiveOrder::addQuantity);

    @Override
    public String toString() {
        return String.format("%s: %s Kg for £%s", orderType, quantityInKgs, pricePerKgInPounds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LiveOrder liveOrder = (LiveOrder) o;

        return new EqualsBuilder()
                .append(quantityInKgs, liveOrder.quantityInKgs)
                .append(pricePerKgInPounds, liveOrder.pricePerKgInPounds)
                .append(orderType, liveOrder.orderType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(quantityInKgs)
                .append(pricePerKgInPounds)
                .append(orderType)
                .toHashCode();
    }



}
