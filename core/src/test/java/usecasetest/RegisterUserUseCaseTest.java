package usecasetest;

import exceptions.BussinessRuleViolatedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.input.RegisterUserInput;
import user.input.UserDTO;
import user.model.User;
import user.model.UserStatus;
import user.repository.RegisterUserRepository;
import user.usecase.RegisterUserUseCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterUserUseCaseTest {
    @Mock
    RegisterUserRepository repository;

    @Test
    public void registerUser_UserNotExists_UserSaved(){
        UserDTO userDTO = new UserDTO("john@example.com", "secret123");
        User user = User.factory("john@example.com", "secret123");
        user.setId(1L);
        RegisterUserInput input = new RegisterUserUseCase(repository);
        when(repository.save(any(User.class))).thenReturn(user);
        UserDTO registered = input.registerUser(userDTO);
        assertAll(
                () -> assertNotNull(registered),
                () -> assertEquals("john@example.com", registered.getEmail()),
                () -> assertEquals("secret123", registered.getPassword()),
                () -> assertEquals(UserStatus.PENDING, registered.getStatus())
        );
    }

    @Test
    public void registerUser_UserEmailExists_ThrowException(){
        UserDTO userDTO = new UserDTO("john@example.com", "secret123");
        when(repository.existEmail(any(String.class))).thenReturn(true);
        RegisterUserInput input = new RegisterUserUseCase(repository);
        Assertions.assertThrows(BussinessRuleViolatedException.class, () -> input.registerUser(userDTO));
    }
}
