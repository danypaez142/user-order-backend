package ar.edu.undec.data.user.repoimplementation;

import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import ar.edu.undec.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.model.User;
import user.repository.RegisterUserRepository;

@Service
public class RegisterUserRepoImplementation implements RegisterUserRepository {
    private UserRepository repository;

    @Autowired
    public RegisterUserRepoImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean existEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.mapCoreToEntity(user);
        entity = repository.save(entity);
        return UserMapper.mapEntityToCore(entity);
    }
}
