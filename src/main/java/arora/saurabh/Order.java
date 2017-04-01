package arora.saurabh;

class Order {
    private final String userId;
    private final Double quantityInKgs;
    private final Integer pricePerKgInPounds;
    private final OrderType orderType;

    Order(String userId, Double quantityInKgs, Integer pricePerKgInPounds, OrderType orderType) {
        this.userId = userId;
        this.quantityInKgs = quantityInKgs;
        this.pricePerKgInPounds = pricePerKgInPounds;
        this.orderType = orderType;
    }

    boolean isOfType(OrderType type) {
        return this.orderType.equals(type);
    }

    LiveOrder liveOrder() {
        return new LiveOrder(quantityInKgs, pricePerKgInPounds, orderType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(userId, order.userId)
                .append(quantityInKgs, order.quantityInKgs)
                .append(pricePerKgInPounds, order.pricePerKgInPounds)
                .append(orderType, order.orderType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(userId)
                .append(quantityInKgs)
                .append(pricePerKgInPounds)
                .append(orderType)
                .toHashCode();
    }

}
