package ar.edu.undec.data.order.mapper;

import ar.edu.undec.data.order.entity.OrderEntity;
import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import order.model.Order;
import user.model.User;

public class OrderMapper {
    public static OrderEntity mapperCoreToEntity(Order order){
        UserEntity user = UserMapper.mapCoreToEntity(order.getUser());
        return new OrderEntity(order.getId(), user, order.getStatus(), order.getAmount(), order.getCreatedAt(), order.getUpdatedAt());
    }

    public static Order mapperEntityToCore(OrderEntity orderEntity){
        User user = UserMapper.mapEntityToCore(orderEntity.getUser());
        return Order.factoryFromEntity(orderEntity.getId(),user,orderEntity.getStatus(),orderEntity.getAmount(),
                orderEntity.getCreatedAt(),orderEntity.getUpdatedAt());
    }
}
