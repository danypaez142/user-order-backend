package ar.edu.undec.service.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.input.RegisterUserInput;
import user.repository.RegisterUserRepository;
import user.usecase.RegisterUserUseCase;

@Configuration
public class UserConfig {
    @Bean
    RegisterUserInput registerUserInput(RegisterUserRepository repository){
        return new RegisterUserUseCase(repository);
    }
}
