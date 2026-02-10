package ar.edu.undec.utils.report;

import ar.edu.undec.service.user.report.UserReport;
import ar.edu.undec.service.user.report.UserReportDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import user.model.User;
import utils.input.PDFGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PDFGeneratorImplementation implements PDFGeneratorInput {
    private final static String reportUsersDir = "/report/usersReport.jasper";
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

    @Override
    public ByteArrayOutputStream generateUsersPDF(List<User> users) throws IOException {
        List<UserReportDetail> details = new ArrayList<>();
        details = users.stream().map(u -> new UserReportDetail(u.getId(),u.getEmail(),dateFormat.format(u.getCreatedAt()),u.getStatus().name()))
                .toList();
        List<UserReport> datasource = new ArrayList<>();
        datasource.add(new UserReport(details));
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        HashMap<String, Object> params = new HashMap<>();
        params.put("subReportPath", "report/");
        params.put("logoUndec", "report/img/logoUndec.png");

        try {
            InputStream reportStream = null;
            reportStream = PDFGeneratorImplementation.class.getResourceAsStream(reportUsersDir);

            JasperReport report = null;
            report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(datasource));
            pdf.write(JasperExportManager.exportReportToPdf(jasperPrint));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pdf;
    }
}
