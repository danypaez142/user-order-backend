package user.repository;

import user.model.User;

import java.util.List;

public interface FindUserRepository {
    User getUserById(Long id);
    List<User> getAllSavedUsers();
}
