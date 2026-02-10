package usecasetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.input.ExportUsersForPDFInput;
import user.model.User;
import user.model.UserStatus;
import user.repository.FindUserRepository;
import user.usecase.ExportUsersForPDFUseCase;
import utils.input.PDFGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExportUsersForPDFUseCaseTest {
    @Mock
    FindUserRepository repository;
    @Mock
    PDFGeneratorInput pdfGeneratorInput;

    ExportUsersForPDFInput input;


    @BeforeEach
    void setup() {input = new ExportUsersForPDFUseCase(repository, pdfGeneratorInput);}

    @Test
    public void getUsersReportPDF_FileGenerated_Successful() throws IOException {
        when(repository.getAllSavedUsers()).thenReturn(getListUsersForReport());
        when(pdfGeneratorInput.generateUsersPDF(anyList())).thenReturn(new ByteArrayOutputStream(1024));
        ByteArrayOutputStream reportPDF = input.generateUsersPDF();
        Assertions.assertNotNull(reportPDF);
    }

    @Test
    public void getUsersReportPDF_ErrorWhilePDFIsGenerated_ThrowException() throws IOException {
        when(pdfGeneratorInput.generateUsersPDF(anyList())).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> pdfGeneratorInput.generateUsersPDF(getListUsersForReport()));
    }


    private List<User> getListUsersForReport(){
        List<User> users = new ArrayList<>();
        users.add(User.factoryFromEntity(1L, "danypaez142@gmail.com", "passw0rd", UserStatus.ACTIVE, "nu11",
                LocalDateTime.of(2025,9,21,14,30),LocalDateTime.of(2025,9,21,14,0)));
        users.add(User.factoryFromEntity(1L, "john@example.com", "secret123", UserStatus.ACTIVE, "3mpty",
                LocalDateTime.of(2026,1,30,14,30),LocalDateTime.of(2026,1,30,14,0)));
        return users;
    }
}
