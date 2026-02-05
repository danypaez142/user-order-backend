package utils.input;

import user.model.User;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PDFGeneratorInput {
    ByteArrayOutputStream generateUsersPDF(List<User> users);
}
