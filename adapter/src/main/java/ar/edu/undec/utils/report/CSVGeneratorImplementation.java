package ar.edu.undec.utils.report;

import order.model.Order;
import org.springframework.stereotype.Service;
import utils.input.CSVGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class CSVGeneratorImplementation implements CSVGeneratorInput {
    @Override
    public ByteArrayOutputStream generateOrdersCSV(List<Order> orders) {
        return null;
    }
}
