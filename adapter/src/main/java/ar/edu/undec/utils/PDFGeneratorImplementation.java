package ar.edu.undec.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import user.model.User;
import utils.input.PDFGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class PDFGeneratorImplementation implements PDFGeneratorInput {
    private final static String reportUsersDir = "/report/usersReport.jasper";

    @Override
    public ByteArrayOutputStream generateUsersPDF(List<User> users) throws IOException {
        ByteArrayOutputStream pdf = null;
        HashMap<String, Object> params = new HashMap<>();
        params.put("subReportPath", "/report/");
        params.put("logoUndec", "report/img/logoUndec.png");


        try {
            InputStream reportStream = null;
            reportStream = PDFGeneratorImplementation.class.getResourceAsStream(reportUsersDir);

            JasperReport report = null;
            report = (JasperReport) JRLoader.loadObject(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(users));
            pdf.write(JasperExportManager.exportReportToPdf(jasperPrint));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return pdf;
    }
}
