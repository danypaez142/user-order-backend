package ar.edu.undec.datatest;

import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import ar.edu.undec.data.user.repoimplementation.ActivateUserRepoImplementation;
import ar.edu.undec.data.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.model.User;
import user.model.UserStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivateUserDataUnitTest {
    @InjectMocks
    ActivateUserRepoImplementation repoImplementation;

    @Mock
    UserRepository userRepository;

    @Test
    public void getUserById_ReturnUserSuccessful(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(getUserEntity()));
        User user = repoImplementation.getUserById(1L);
        assertNotNull(user);
    }
    
    @Test
    public void getUserById_DataBaseError_ThrowException(){
        when(userRepository.findById(1L)).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> repoImplementation.getUserById(1L));
    }

    @Test
    public void updateUserActivated_UserSaved_ReturnUserUpdated(){
        UserEntity entity = getUserEntity();
        entity.setStatus(UserStatus.ACTIVE);
        when(userRepository.save(any(UserEntity.class))).thenReturn(entity);
        User userUpdated = repoImplementation.updateUser(UserMapper.mapEntityToCore(getUserEntity()));
        Assertions.assertAll(
                () -> assertNotNull(userUpdated),
                () -> assertEquals(entity.getId(), userUpdated.getId()));
    }

    private UserEntity getUserEntity(){
        LocalDateTime date = LocalDateTime.of(2026,1,3,14,25,29);
        return new UserEntity(1L,"danypaez142@gmail.com","passw0rd", UserStatus.PENDING,
                "aa1234",date.plusMinutes(30), date);
    }
}
