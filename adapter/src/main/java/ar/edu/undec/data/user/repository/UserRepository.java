package ar.edu.undec.data.user.repository;

import ar.edu.undec.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Boolean findByEmail(String email);
}
