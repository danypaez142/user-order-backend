package ar.edu.undec.data.order.entity;

import ar.edu.undec.data.user.entity.UserEntity;
import jakarta.persistence.*;
import order.model.OrderStatus;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity(name="orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="users_id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatus status;
    @NotNull
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderEntity() {
    }

    public OrderEntity(Long id, UserEntity user, OrderStatus status, Double amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
