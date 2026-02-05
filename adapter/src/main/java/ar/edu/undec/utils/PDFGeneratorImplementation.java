package ar.edu.undec.utils;

import org.springframework.stereotype.Service;
import user.model.User;
import utils.input.PDFGeneratorInput;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PDFGeneratorImplementation implements PDFGeneratorInput {
    @Override
    public ByteArrayOutputStream generateUsersPDF(List<User> users) {
        return null;
    }
}
