package usecasetest;

import order.input.ExportOrderForCSVInput;
import order.model.Order;
import order.repository.FindOrdersRepository;
import order.usecase.ExportOrdersForCSVUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user.model.User;
import user.model.UserStatus;
import utils.input.CSVGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExportOrdersForCSVUseCaseTest {
    @Mock
    FindOrdersRepository ordersRepository;
    @Mock
    CSVGeneratorInput csvGeneratorInput;

    ExportOrderForCSVInput input;

    @BeforeEach
    void setup(){input = new ExportOrdersForCSVUseCase(ordersRepository, csvGeneratorInput);}

    @Test
    public void getUsersReportPDF_FileGenerated_Successful() throws IOException {
        when(ordersRepository.getAllSavedOrders()).thenReturn(getListOrdersForReport());
        when(csvGeneratorInput.generateOrdersCSV(anyList())).thenReturn(new ByteArrayOutputStream(1024));
        ByteArrayOutputStream reportPDF = input.generateOrdersCSV();
        Assertions.assertNotNull(reportPDF);
    }

    @Test
    public void getUsersReportPDF_ErrorWhilePDFIsGenerated_ThrowException() throws IOException {
        when(csvGeneratorInput.generateOrdersCSV(anyList())).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> csvGeneratorInput.generateOrdersCSV(getListOrdersForReport()));
    }

    private List<Order> getListOrdersForReport() {
        List<Order> orders = new ArrayList<>();
        User first = User.factoryFromEntity(1L, "danypaez142@gmail.com", "passw0rd", UserStatus.ACTIVE, "nu11",
                LocalDateTime.of(2025,9,21,14,30),LocalDateTime.of(2025,9,21,14,0));
        User second = User.factoryFromEntity(2L, "john@example.com", "secret123", UserStatus.ACTIVE, "3mpty",
                LocalDateTime.of(2026,1,30,14,30),LocalDateTime.of(2026,1,30,14,0));

        orders.add(Order.factory(first, 99.85));
        orders.add(Order.factory(first, 47.95));
        orders.add(Order.factory(second, 150.78));
        return orders;
    }

}
