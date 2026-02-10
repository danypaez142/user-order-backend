package ar.edu.undec.service.order.controller;

import order.input.ExportOrderForCSVInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("users/orders/csv")
public class ExportOrdersForCSVController {
    ExportOrderForCSVInput input;

    public ExportOrdersForCSVController(ExportOrderForCSVInput input) {
        this.input = input;
    }

    @GetMapping
    public ResponseEntity<?> exportOrdersForCSV() {
        try{
            ByteArrayOutputStream csv = input.generateOrdersCSV();
            return responseReport(csv.toByteArray());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ResponseEntity<?> responseReport(byte[] reporte) {
        if(reporte == null){
            return ResponseEntity.noContent().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/csv"));
        String csvNameFile = "Lista de Ordenes.csv";
        headers.setContentDispositionFormData(csvNameFile,csvNameFile);
        return new ResponseEntity<byte[]>(reporte, headers, HttpStatus.OK);
    }
}
