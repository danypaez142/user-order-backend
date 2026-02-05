package order.repository;

import order.model.Order;

public interface CreateOrderRepository {
    Order saveOrder(Order order);
}
