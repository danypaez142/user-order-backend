package order.input;

import java.io.ByteArrayOutputStream;

public interface ExportOrderForCSVInput {
    ByteArrayOutputStream generateOrdersCSV();
}
