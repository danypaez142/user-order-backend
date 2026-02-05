package order.usecase;

import exceptions.BussinessRuleViolatedException;
import order.input.CreateOrderInput;
import order.input.OrderDTO;
import order.model.Order;
import order.repository.CreateOrderRepository;
import user.model.User;
import user.model.UserStatus;
import user.repository.FindUserRepository;

public class CreateOrderUseCase implements CreateOrderInput {
    private final CreateOrderRepository repository;
    private final FindUserRepository userRepository;

    public CreateOrderUseCase(CreateOrderRepository repository, FindUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order;
        User user = userRepository.getUserById(orderDTO.getUser().getId());
        if(user != null && user.getStatus().equals(UserStatus.ACTIVE)){
            order = Order.factory(user, orderDTO.getAmount());
            order = repository.saveOrder(order);
            return order;
        }else{
            throw new BussinessRuleViolatedException("User's status can't create orders");
        }
    }
}
