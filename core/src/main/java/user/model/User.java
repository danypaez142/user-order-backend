package user.model;

import exceptions.ValidationException;
import utils.ActivationCodeGenerator;
import utils.MailValidator;

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

    private User(Long id, String email, String password, UserStatus status, String activationCode, LocalDateTime activationExpiresAt, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.activationCode = activationCode;
        this.activationExpiresAt = activationExpiresAt;
        this.createdAt = createdAt;
    }

    public static User factory(String email, String password) {
        if(email == null || email.isEmpty()){
            throw new ValidationException("email is required");
        }else if(!MailValidator.isValid(email)){
            throw new ValidationException("invalid email");
        }
        if(password == null || password.isEmpty()) {
            throw new ValidationException("password is required");
        }
        return new User(email, password);
    }

    public static User factoryFromEntity(Long id, String email, String password, UserStatus status, String activationCode,
                                         LocalDateTime activationExpiresAt, LocalDateTime createdAt){
        return new User(id, email, password, status, activationCode, activationExpiresAt, createdAt);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){ this.id = id;}

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

    public void setStatus(UserStatus userStatus) {
        this.status = userStatus;
    }
}
