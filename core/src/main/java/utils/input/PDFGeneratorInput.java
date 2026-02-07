package utils.input;

import user.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface PDFGeneratorInput {
    ByteArrayOutputStream generateUsersPDF(List<User> users) throws IOException;
}
