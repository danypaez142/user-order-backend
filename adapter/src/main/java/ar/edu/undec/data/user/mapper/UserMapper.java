package ar.edu.undec.data.user.mapper;

import ar.edu.undec.data.user.entity.UserEntity;
import user.model.User;

public class UserMapper {
    public static UserEntity mapCoreToEntity(User user){
        return new UserEntity(user.getId(), user.getEmail(), user.getPassword(), user.getStatus(),
                user.getActivationCode(), user.getActivationExpiresAt(), user.getCreatedAt());
    }

    public static User mapEntityToCore(UserEntity userEntity){
        return User.factoryFromEntity(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getStatus(),
                userEntity.getActivationCode(), userEntity.getActivationExpiresAt(), userEntity.getCreatedAt());
    }
}
