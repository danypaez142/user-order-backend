package ar.edu.undec.service.user.report;

import java.util.List;

public class UserReport {
    private List<UserReportDetail> usuarios;

    public UserReport() {
    }

    public UserReport(List<UserReportDetail> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UserReportDetail> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserReportDetail> usuarios) {
        this.usuarios = usuarios;
    }
}
