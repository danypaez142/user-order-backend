package order.model;

import exceptions.ValidationException;
import user.model.User;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private User user;
    private OrderStatus status;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Order(User user, Double amount) {
        this.user = user;
        this.amount = amount;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public static Order factory(User user, Double amount) {
        if(amount == null || amount == 0.0){
            throw new ValidationException("Order's amount can't be null or 0");
        }

        return new Order(user, amount);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
