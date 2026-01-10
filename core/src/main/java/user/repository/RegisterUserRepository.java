package user.repository;

import user.model.User;

public interface RegisterUserRepository {
    Boolean existEmail(String email);
    User save(User user);
}
