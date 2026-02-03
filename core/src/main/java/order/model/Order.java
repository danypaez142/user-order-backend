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

    private Order(Long id, User user, OrderStatus status, Double amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Order factory(User user, Double amount) {
        if(amount == null || amount == 0.0){
            throw new ValidationException("Order's amount can't be null or 0");
        }

        return new Order(user, amount);
    }

    public static Order factoryFromEntity(Long id, User user, OrderStatus status, Double amount, LocalDateTime createdAt,
                                          LocalDateTime updatedAt){
        return new Order(id, user,status,amount,createdAt,updatedAt);
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
