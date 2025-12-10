package modeltest;

import exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void instanceUser_WrongMail_ThrowsException() {
        //Null email
        Assertions.assertThrows(ValidationException.class, () -> User.factory(null, "secret123"));

        //Empty email
        Assertions.assertThrows(ValidationException.class, () -> User.factory("", "secret123"));

        //Invalid email
        Assertions.assertThrows(ValidationException.class, () -> User.factory(".@example.com", "secret123"));
    }

    @Test
    public void instanceUser_WrongPassword_ThrowsException() {
        //Null password
        Assertions.assertThrows(ValidationException.class, () -> User.factory("john@example.com", null));

        //Empty password
        Assertions.assertThrows(ValidationException.class, () -> User.factory("john@example.com", ""));
    }
}
