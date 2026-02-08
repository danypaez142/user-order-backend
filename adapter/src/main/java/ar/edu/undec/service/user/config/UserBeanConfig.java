package ar.edu.undec.service.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.input.ActivateUserInput;
import user.input.ExportUsersForPDFInput;
import user.input.RegisterUserInput;
import user.repository.ActivateUserRepository;
import user.repository.FindUserRepository;
import user.repository.RegisterUserRepository;
import user.usecase.ActivateUserUseCase;
import user.usecase.ExportUsersForPDFUseCase;
import user.usecase.RegisterUserUseCase;
import utils.input.PDFGeneratorInput;

@Configuration
public class UserBeanConfig {
    @Bean
    RegisterUserInput registerUserInput(RegisterUserRepository repository){
        return new RegisterUserUseCase(repository);
    }

    @Bean
    ActivateUserInput activateUserInput(ActivateUserRepository repository){
        return new ActivateUserUseCase(repository);
    }

    @Bean
    ExportUsersForPDFInput exportUsersForPDFInput(FindUserRepository findUserRepository, PDFGeneratorInput pdfGeneratorInput){
        return new ExportUsersForPDFUseCase(findUserRepository,pdfGeneratorInput);
    }
}
