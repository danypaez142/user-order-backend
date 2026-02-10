package ar.edu.undec.data.order.repoimplementation;

import ar.edu.undec.data.order.entity.OrderEntity;
import ar.edu.undec.data.order.mapper.OrderMapper;
import ar.edu.undec.data.order.repository.OrderRepository;
import order.model.Order;
import order.repository.FindOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindOrdersRepoImplementation implements FindOrdersRepository {
    private final OrderRepository repository;

    @Autowired
    public FindOrdersRepoImplementation(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getAllSavedOrders() {
        List<Order> orders = new ArrayList<>();
        Iterable<OrderEntity> ordersEntity = repository.findAll();
        for(OrderEntity entity : ordersEntity){
            orders.add(OrderMapper.mapperEntityToCore(entity));
        }
        return orders;
    }
}
