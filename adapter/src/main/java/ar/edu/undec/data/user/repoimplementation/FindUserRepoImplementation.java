package ar.edu.undec.data.user.repoimplementation;

import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import ar.edu.undec.data.user.repository.UserRepository;
import user.model.User;
import user.repository.FindUserRepository;

import java.util.Optional;

public class FindUserRepoImplementation implements FindUserRepository {
    private final UserRepository repository;

    public FindUserRepoImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> userEntity = repository.findById(id);
        return userEntity.map(UserMapper::mapEntityToCore).orElse(null);
    }
}
