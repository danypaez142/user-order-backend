package ar.edu.undec.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.input.ExportUsersForPDFInput;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("users/export/pdf")
public class ExportUsersForPDFController {
    ExportUsersForPDFInput input;

    @Autowired
    public ExportUsersForPDFController(ExportUsersForPDFInput input) {
        this.input = input;
    }

    @GetMapping
    public ResponseEntity<?> getUsersPDF(){
        try{
            ByteArrayOutputStream pdf = input.generateUsersPDF();
            return responseReport(pdf.toByteArray());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ResponseEntity<?> responseReport(byte[] reporte) {
        if(reporte == null){
            return ResponseEntity.noContent().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String nombrePDF = "Lista de Usuarios.pdf";
        headers.setContentDispositionFormData(nombrePDF,nombrePDF);
        return new ResponseEntity<byte[]>(reporte, headers, HttpStatus.OK);
    }
}
