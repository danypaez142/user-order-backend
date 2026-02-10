package utils.input;

import order.model.Order;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface CSVGeneratorInput {
    ByteArrayOutputStream generateOrdersCSV(List<Order> orders) throws IOException;
}
