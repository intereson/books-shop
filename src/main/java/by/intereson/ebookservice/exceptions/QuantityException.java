package by.intereson.ebookservice.exceptions;

public class QuantityException extends RuntimeException {
    public QuantityException(Integer message) {
        super(message.toString());
    }
}
