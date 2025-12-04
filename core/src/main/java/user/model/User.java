package user.model;

import exceptions.ValidationException;
import utils.ActivationCodeGenerator;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String email;
    private String password;
    private UserStatus status;
    private String activationCode;
    private LocalDateTime activationExpiresAt;
    private LocalDateTime createdAt;

    private User(String email, String password) {
        this.email = email;
        this.password = password;
        this.status = UserStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.activationExpiresAt = this.createdAt.plusMinutes(30);
        this.activationCode = ActivationCodeGenerator.generateActivationCode();
    }
    public static User factory(String email, String password) {
        if(email == null || email.isEmpty()){
            throw new ValidationException("email is required");
        }
        if(password == null || password.isEmpty()) {
            throw new ValidationException("password is required");
        }
        return new User(email, password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public LocalDateTime getActivationExpiresAt() {
        return activationExpiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
