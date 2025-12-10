package utils;

import java.util.UUID;

public class ActivationCodeGenerator {
    public static String generateActivationCode() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
