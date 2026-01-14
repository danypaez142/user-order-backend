package user.usecase;

import exceptions.ValidationException;
import user.input.ActivateUserInput;
import user.input.UserDTO;
import user.model.User;
import user.model.UserStatus;
import user.repository.ActivateUserRepository;

import java.time.*;

public class ActivateUserUseCase implements ActivateUserInput {
    private final ActivateUserRepository repository;

    public ActivateUserUseCase(ActivateUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean activateUser(UserDTO user) {
        User userForActivate = repository.getUserById(user.getId());
        if(userForActivate != null && isCorrectActivationCode(user, userForActivate)) {
            if(isUserExpired(userForActivate)){
                userForActivate.setStatus(UserStatus.EXPIRED);
            }else{
                userForActivate.setStatus(UserStatus.ACTIVE);
            }
        }else{
            throw new ValidationException("Activation Code provides don't match");
        }

        userForActivate = repository.updateUser(userForActivate);
        return userForActivate.getStatus().equals(UserStatus.ACTIVE);
    }

    private boolean isCorrectActivationCode(UserDTO user, User userForActivate) {
        return user != null && userForActivate != null && user.getActivationCode() != null
                && !user.getActivationCode().isBlank()
                && userForActivate.getActivationCode().trim().toLowerCase().equals(user.getActivationCode().trim().toLowerCase());
    }

    private boolean isUserExpired(User user) {
        Instant instant = Clock.systemDefaultZone().instant();
        LocalDateTime now = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return now.isAfter(user.getActivationExpiresAt());
    }
}
