package by.intereson.ebookservice.exceptions;

public class ShoppingCartIsEmptyException extends RuntimeException {
    public ShoppingCartIsEmptyException(Long message) {
        super(message.toString());
    }
}
