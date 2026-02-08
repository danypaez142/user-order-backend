package ar.edu.undec.service.user.report;

import java.time.LocalDateTime;

public class UserReportDetail {
    private Long id;
    private String email;
    private String createdAt;
    private String status;

    public UserReportDetail() {
    }

    public UserReportDetail(Long id, String email, String createdAt, String status) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
