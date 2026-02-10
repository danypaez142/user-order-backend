package ar.edu.undec.utils.report;

import order.model.Order;
import org.springframework.stereotype.Service;
import utils.input.CSVGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CSVGeneratorImplementation implements CSVGeneratorInput {
    @Override
    public ByteArrayOutputStream generateOrdersCSV(List<Order> orders) throws IOException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder builder = new StringBuilder();
        builder.append("Order ID").append(",")
                .append("User Email").append(",")
                .append("Order Status").append(",")
                .append("Amount").append(",")
                .append("Created Date").append(",")
                .append("Last Update Date").append("\n");



        if(orders == null || orders.isEmpty()) {
            return null;
        }

        for(Order order : orders) {
            builder.append(order.getId()).append(",")
                    .append(order.getUser().getEmail()).append(",")
                    .append(order.getStatus().name()).append(",")
                    .append("$").append(order.getAmount()).append(",")
                    .append(dateFormat.format(order.getCreatedAt())).append(",")
                    .append(order.getUpdatedAt() == null ? "" : dateFormat.format(order.getUpdatedAt())).append("\n");
        }

        ByteArrayOutputStream csv = new ByteArrayOutputStream();
        csv.write(builder.toString().getBytes());
        return csv;
    }
}
