package modeltest;

import exceptions.ValidationException;
import order.model.Order;
import order.model.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import user.model.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderUnitTest {
    @Test
    public void instanceOrder_CorrectInstance(){
        User user = User.factory("john@example.com", "secret123");
        Order order = Order.factory(user, 199.90);
        assertAll(
                () -> assertNotNull(order),
                () -> assertEquals(user, order.getUser()),
                () -> assertEquals(199.90, order.getAmount()),
                () -> assertEquals(OrderStatus.PENDING, order.getStatus())
        );
        System.out.println(order.getCreatedAt());
    }

    @Test
    public void instanceOrder_WrongAmount_ThrowException(){
        User user = User.factory("john@example.com", "secret123");

        //Amount null
        Assertions.assertThrows(ValidationException.class, () -> Order.factory(user, null));

        //Amount 0
        Assertions.assertThrows(ValidationException.class, () -> Order.factory(user, 0.0));
    }
}
