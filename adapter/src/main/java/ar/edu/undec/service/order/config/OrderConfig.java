package ar.edu.undec.service.order.config;

import order.input.CreateOrderInput;
import order.repository.CreateOrderRepository;
import order.usecase.CreateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.repository.FindUserRepository;

@Configuration
public class OrderConfig {
    @Bean
    public CreateOrderInput createOrderInput(CreateOrderRepository orderRepository, FindUserRepository userRepository){
        return new CreateOrderUseCase(orderRepository,userRepository);
    }
}
