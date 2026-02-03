package ar.edu.undec.data.user.repoimplementation;

import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import ar.edu.undec.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.model.User;
import user.repository.ActivateUserRepository;

import java.util.Optional;

@Service
public class ActivateUserRepoImplementation implements ActivateUserRepository {
    private final UserRepository repository;

    @Autowired
    public ActivateUserRepoImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.map(UserMapper::mapEntityToCore).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        UserEntity entity = UserMapper.mapCoreToEntity(user);
        entity = repository.save(entity);
        return UserMapper.mapEntityToCore(entity);
    }
}
