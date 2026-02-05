package order.input;

import order.model.Order;

public interface CreateOrderInput {
    Order createOrder(OrderDTO orderDTO);
}
