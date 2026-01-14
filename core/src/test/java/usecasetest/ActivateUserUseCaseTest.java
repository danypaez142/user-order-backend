package usecasetest;

import exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.input.ActivateUserInput;
import user.input.UserDTO;
import user.model.User;
import user.model.UserStatus;
import user.repository.ActivateUserRepository;
import user.usecase.ActivateUserUseCase;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivateUserUseCaseTest {
    @Mock
    ActivateUserRepository repository;

    ActivateUserInput input;

    @BeforeEach
    void setUp() {input = new ActivateUserUseCase(repository);}

    @Test
    public void activateUser_UserActivatedAtTime_UserActivated(){
        UserDTO dto = new UserDTO("john@example.com", "secret123");
        dto.setActivationCode("681fb5e1-1");
        dto.setId(1L);

        when(repository.getUserById(any(Long.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(), dto.getPassword(),
                UserStatus.PENDING, dto.getActivationCode(), LocalDateTime.now().plusMinutes(10), LocalDateTime.now()));
        when(repository.updateUser(any(User.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(), dto.getPassword(),
                UserStatus.ACTIVE, dto.getActivationCode(), LocalDateTime.now().plusMinutes(10), LocalDateTime.now()));

        Boolean isActive = input.activateUser(dto);
        Assertions.assertTrue(isActive);
    }

    @Test
    public void activateUser_UserActivatedAfterTime_UserNotActivated(){
        UserDTO dto = new UserDTO("john@example.com", "secret123");
        dto.setActivationCode("681fb5e1-1");
        dto.setId(1L);
        dto.setCreatedAt(LocalDateTime.of(2026,01,14,0,0,0));
        dto.setActivationExpiresAt(dto.getCreatedAt().plusMinutes(30));
        dto.setStatus(UserStatus.PENDING);

        when(repository.getUserById(any(Long.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(), dto.getPassword(),
                dto.getStatus(), dto.getActivationCode(), dto.getActivationExpiresAt(), dto.getCreatedAt()));
        when(repository.updateUser(any(User.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(), dto.getPassword(),
                UserStatus.EXPIRED, dto.getActivationCode(), dto.getActivationExpiresAt(), dto.getCreatedAt()));

        Boolean isActive = input.activateUser(dto);
        Assertions.assertFalse(isActive);
    }

    @Test
    public void activateUser_InvalidActivationCode_ThrowException(){
        UserDTO dto = new UserDTO("john@example.com", "secret123");
        dto.setActivationCode("681fc5e1-1");
        dto.setId(1L);
        dto.setCreatedAt(LocalDateTime.of(2026,01,14,0,0,0));
        dto.setActivationExpiresAt(dto.getCreatedAt().plusMinutes(10));
        dto.setStatus(UserStatus.PENDING);

        when(repository.getUserById(any(Long.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(),
                dto.getPassword(), dto.getStatus(), "681fb5e1-1", dto.getActivationExpiresAt(),
                dto.getCreatedAt()));

        Assertions.assertThrows(ValidationException.class, () -> input.activateUser(dto));
    }

    @Test
    public void activateUser_NullActivationCodeProvided_ThrowException(){
        UserDTO dto = new UserDTO("john@example.com", "secret123");
        dto.setActivationCode(null);
        dto.setId(1L);
        dto.setCreatedAt(LocalDateTime.of(2026,01,14,0,0,0));
        dto.setActivationExpiresAt(dto.getCreatedAt().plusMinutes(10));
        dto.setStatus(UserStatus.PENDING);

        when(repository.getUserById(any(Long.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(),
                dto.getPassword(), dto.getStatus(), "681fb5e1-1", dto.getActivationExpiresAt(),
                dto.getCreatedAt()));

        Assertions.assertThrows(ValidationException.class, () -> input.activateUser(dto));
    }

    @Test
    public void activateUser_EmptyActivationCodeProvided_ThrowException(){
        UserDTO dto = new UserDTO("john@example.com", "secret123");
        dto.setActivationCode("");
        dto.setId(1L);
        dto.setCreatedAt(LocalDateTime.of(2026,01,14,0,0,0));
        dto.setActivationExpiresAt(dto.getCreatedAt().plusMinutes(10));
        dto.setStatus(UserStatus.PENDING);

        when(repository.getUserById(any(Long.class))).thenReturn(User.factoryFromEntity(1l, dto.getEmail(),
                dto.getPassword(), dto.getStatus(), "681fb5e1-1", dto.getActivationExpiresAt(),
                dto.getCreatedAt()));

        Assertions.assertThrows(ValidationException.class, () -> input.activateUser(dto));
    }
}
