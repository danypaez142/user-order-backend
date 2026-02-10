package order.usecase;

import order.input.ExportOrderForCSVInput;
import order.repository.FindOrdersRepository;
import utils.input.CSVGeneratorInput;

import java.io.ByteArrayOutputStream;

public class ExportOrdersForCSVUseCase implements ExportOrderForCSVInput {
    private final FindOrdersRepository ordersRepository;
    private final CSVGeneratorInput csvGeneratorInput;

    public ExportOrdersForCSVUseCase(FindOrdersRepository ordersRepository, CSVGeneratorInput csvGeneratorInput) {
        this.ordersRepository = ordersRepository;
        this.csvGeneratorInput = csvGeneratorInput;
    }

    @Override
    public ByteArrayOutputStream generateOrdersCSV() {
        try{
            return csvGeneratorInput.generateOrdersCSV(ordersRepository.getAllSavedOrders());
        }catch(Exception e){
            throw new RuntimeException("Error generating CSV: " + e.getMessage());
        }

    }
}
