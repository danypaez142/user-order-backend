package user.repository;

import user.model.User;

public interface FindUserRepository {
    User getUserById(Long id);
}
