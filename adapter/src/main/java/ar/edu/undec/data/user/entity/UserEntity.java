package ar.edu.undec.data.user.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import user.model.UserStatus;

import java.time.LocalDateTime;

@Entity(name = "users")
public class UserEntity {
    @Id
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Enumerated
    @Column(nullable = false)
    private UserStatus status;
    private String activationCode;
    private LocalDateTime activationExpiresAt;
    @NotNull
    private LocalDateTime createdAt;

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String password, UserStatus status, String activationCode,
                      LocalDateTime activationExpiresAt, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.activationCode = activationCode;
        this.activationExpiresAt = activationExpiresAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public LocalDateTime getActivationExpiresAt() {
        return activationExpiresAt;
    }

    public void setActivationExpiresAt(LocalDateTime activationExpiresAt) {
        this.activationExpiresAt = activationExpiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
