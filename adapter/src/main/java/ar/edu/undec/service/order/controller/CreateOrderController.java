package ar.edu.undec.service.order.controller;

import order.input.CreateOrderInput;
import order.input.OrderDTO;
import order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.input.UserDTO;

@RestController
@RequestMapping("users/{userId}/orders")
public class CreateOrderController {
    CreateOrderInput input;

    @Autowired
    public CreateOrderController(CreateOrderInput input) {
        this.input = input;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@PathVariable(value = "userId") Long userID, @RequestBody OrderDTO orderDTO){
        try{
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userID);
            orderDTO.setUser(userDTO);

            Order order = input.createOrder(orderDTO);

            orderDTO = getOrderDTO(order);
            return ResponseEntity.ok().body(orderDTO);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private OrderDTO getOrderDTO(Order order) {
        UserDTO userDTO = new UserDTO(order.getUser().getId(),order.getUser().getEmail(),order.getUser().getPassword(),
                order.getUser().getStatus(), order.getUser().getActivationCode(),order.getUser().getActivationExpiresAt(), order.getUser().getCreatedAt());
        return new OrderDTO(order.getId(), userDTO, order.getStatus(), order.getAmount(), order.getCreatedAt(), order.getUpdatedAt());
    }

}
