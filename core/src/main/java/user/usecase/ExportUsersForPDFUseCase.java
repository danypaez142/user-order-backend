package user.usecase;

import user.input.ExportUsersForPDFInput;
import user.model.User;
import user.repository.FindUserRepository;
import utils.input.PDFGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExportUsersForPDFUseCase implements ExportUsersForPDFInput {
    private final FindUserRepository repository;
    private final PDFGeneratorInput pdfGeneratorInput;

    public ExportUsersForPDFUseCase(FindUserRepository repository, PDFGeneratorInput pdfGeneratorInput) {
        this.repository = repository;
        this.pdfGeneratorInput = pdfGeneratorInput;
    }

    @Override
    public ByteArrayOutputStream generateUsersPDF() {
        try{
            return pdfGeneratorInput.generateUsersPDF(repository.getAllSavedUsers());
        }catch(Exception e){
            throw new RuntimeException("Error generating PDF: " + e.getMessage());
        }
    }
}
