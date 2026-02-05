package ar.edu.undec.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.input.ExportUsersForPDFInput;

@RestController
@RequestMapping("users/pdf")
public class ExportUsersForPDFController {
    ExportUsersForPDFInput input;

    @Autowired
    public ExportUsersForPDFController(ExportUsersForPDFInput input) {
        this.input = input;
    }

    @GetMapping
    public ResponseEntity<?> getUsersPDF(){
        try{
            return ResponseEntity.ok(input.generateUsersPDF());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
