package user.repository;

import user.input.UserDTO;
import user.model.User;

public interface ActivateUserRepository {
    User getUserById(Long id);
    User updateUser(User user);
}
