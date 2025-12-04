package modeltest;

import org.junit.jupiter.api.Test;
import user.model.User;
import user.model.UserStatus;

import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTest {
    @Test
    public void instanceUser_InstantiatesCorrectly() {
        User user = User.factory("john@example.com", "secret123");
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals("john@example.com", user.getEmail()),
                () -> assertEquals("secret123",user.getPassword()),
                () -> assertEquals(UserStatus.PENDING, user.getStatus()),
                () -> assertNotNull(user.getCreatedAt())
        );
        System.out.println(user.getActivationCode());
    }
}
