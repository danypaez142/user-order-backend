package user.usecase;

import exceptions.BussinessRuleViolatedException;
import user.input.RegisterUserInput;
import user.input.UserDTO;
import user.model.User;
import user.repository.RegisterUserRepository;

public class RegisterUserUseCase implements RegisterUserInput {
    private final RegisterUserRepository repository;

    public RegisterUserUseCase(RegisterUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = User.factory(userDTO.getEmail(), userDTO.getPassword());

        if(repository.existEmail(user.getEmail())){
            throw new BussinessRuleViolatedException("Email is already registered for an user");
        }
        user = repository.save(user);

        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getStatus(), user.getActivationCode(),
                user.getActivationExpiresAt(), user.getCreatedAt());
    }
}
