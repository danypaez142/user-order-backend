package exceptions;

public class BussinessRuleViolatedException extends RuntimeException {
    public BussinessRuleViolatedException(String message) {
        super(message);
    }
}
