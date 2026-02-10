package ar.edu.undec.service.order.config;

import order.input.CreateOrderInput;
import order.input.ExportOrderForCSVInput;
import order.repository.CreateOrderRepository;
import order.repository.FindOrdersRepository;
import order.usecase.CreateOrderUseCase;
import order.usecase.ExportOrdersForCSVUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.repository.FindUserRepository;
import utils.input.CSVGeneratorInput;

@Configuration
public class OrderConfig {
    @Bean
    public CreateOrderInput createOrderInput(CreateOrderRepository orderRepository, FindUserRepository userRepository){
        return new CreateOrderUseCase(orderRepository,userRepository);
    }

    @Bean
    public ExportOrderForCSVInput exportOrderForCSVInput(FindOrdersRepository ordersRepository, CSVGeneratorInput csvInput){
        return new ExportOrdersForCSVUseCase(ordersRepository, csvInput);
    }
}
