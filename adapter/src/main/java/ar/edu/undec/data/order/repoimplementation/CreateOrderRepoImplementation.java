package ar.edu.undec.data.order.repoimplementation;

import ar.edu.undec.data.order.entity.OrderEntity;
import ar.edu.undec.data.order.mapper.OrderMapper;
import ar.edu.undec.data.order.repository.OrderRepository;
import order.model.Order;
import order.repository.CreateOrderRepository;

public class CreateOrderRepoImplementation implements CreateOrderRepository {
    private final OrderRepository repository;

    public CreateOrderRepoImplementation(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = OrderMapper.mapperCoreToEntity(order);
        entity = repository.save(entity);
        return OrderMapper.mapperEntityToCore(entity);
    }
}
