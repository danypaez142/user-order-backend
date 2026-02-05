package ar.edu.undec.data.user.repoimplementation;

import ar.edu.undec.data.user.entity.UserEntity;
import ar.edu.undec.data.user.mapper.UserMapper;
import ar.edu.undec.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.model.User;
import user.repository.FindUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindUserRepoImplementation implements FindUserRepository {
    private final UserRepository repository;

    @Autowired
    public FindUserRepoImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> userEntity = repository.findById(id);
        return userEntity.map(UserMapper::mapEntityToCore).orElse(null);
    }

    @Override
    public List<User> getAllSavedUsers() {
        List<User> users = new ArrayList<>();
        Iterable<UserEntity> saved = repository.findAll();
        for(UserEntity entity : saved){
            users.add(UserMapper.mapEntityToCore(entity));
        }
        return users;
    }
}
