package order.repository;

import order.model.Order;

import java.util.List;

public interface FindOrdersRepository {
    List<Order> getAllSavedOrders();
}
