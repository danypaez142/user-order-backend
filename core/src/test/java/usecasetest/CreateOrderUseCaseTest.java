package usecasetest;

import exceptions.BussinessRuleViolatedException;
import exceptions.ValidationException;
import order.input.CreateOrderInput;
import order.input.OrderDTO;
import order.model.Order;
import order.repository.CreateOrderRepository;
import order.usecase.CreateOrderUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.input.UserDTO;
import user.model.User;
import user.model.UserStatus;
import user.repository.FindUserRepository;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseTest {
    @Mock
    CreateOrderRepository repository;

    @Mock
    FindUserRepository userRepository;

    CreateOrderInput input;

    @BeforeEach
    void setup(){this.input = new CreateOrderUseCase(repository, userRepository);}

    @Test
    public void createOrder_CorrectAmount_OrderCreated(){
        UserDTO userDTO = new UserDTO(1L,"john@example.com", "secret123", UserStatus.ACTIVE,"aa0000",
                LocalDateTime.of(2026,01,14,0,30,0),
                LocalDateTime.of(2026,01,14,0,0,0));
        OrderDTO orderDTO = new OrderDTO(199.90);
        orderDTO.setUser(userDTO);

        when(userRepository.getUserById(1L)).thenReturn(getUser());
        when(repository.saveOrder(any(Order.class))).thenReturn(getOrder(getUser(),199.90));

        Order order = input.createOrder(orderDTO);

        Assertions.assertNotNull(order);
    }

    @Test
    public void createOrder_Wrong_OrderCreated(){
        UserDTO userDTO = new UserDTO(1L,"john@example.com", "secret123", UserStatus.ACTIVE,"aa0000",
                LocalDateTime.of(2026,01,14,0,30,0),
                LocalDateTime.of(2026,01,14,0,0,0));

        OrderDTO orderNullAmountDTO = new OrderDTO(null);
        orderNullAmountDTO.setUser(userDTO);

        OrderDTO orderZeroAmountDTO = new OrderDTO(0.0);
        orderZeroAmountDTO.setUser(userDTO);

        when(userRepository.getUserById(1L)).thenReturn(getUser());

        Assertions.assertThrows(ValidationException.class, ()-> input.createOrder(orderNullAmountDTO));
        Assertions.assertThrows(ValidationException.class, ()-> input.createOrder(orderZeroAmountDTO));
    }

    @Test
    public void createOrder_UserStatusIsPending_ThrowException(){
        User pending = getUser();
        pending.setId(2L);
        pending.setStatus(UserStatus.PENDING);

        UserDTO userPending = new UserDTO();
        userPending.setId(2L);
        userPending.setStatus(UserStatus.PENDING);

        when(userRepository.getUserById(2L)).thenReturn(pending);

        OrderDTO orderForPendingDTO = new OrderDTO(199.90);
        orderForPendingDTO.setUser(userPending);

        Assertions.assertThrows(BussinessRuleViolatedException.class, ()-> input.createOrder(orderForPendingDTO));
    }

    @Test
    public void createOrder_UserStatusIsExpired_ThrowException(){
        User expired = getUser();
        expired.setId(3L);
        expired.setStatus(UserStatus.EXPIRED);

        UserDTO userExpired = new UserDTO();
        userExpired.setId(3L);
        userExpired.setStatus(UserStatus.EXPIRED);

        when(userRepository.getUserById(3L)).thenReturn(expired);

        OrderDTO orderForExpiredDTO = new OrderDTO(199.90);
        orderForExpiredDTO.setUser(userExpired);
        Assertions.assertThrows(BussinessRuleViolatedException.class, ()-> input.createOrder(orderForExpiredDTO));
    }

    private User getUser() {
        return User.factoryFromEntity(1L,"john@example.com", "secret123", UserStatus.ACTIVE,"aa0000",
                LocalDateTime.of(2026,01,14,0,30,0),
                LocalDateTime.of(2026,01,14,0,0,0));
    }

    private Order getOrder(User user, Double amount){
        return Order.factory(user, amount);
    }
}
