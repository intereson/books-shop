package by.intereson.ebookservice.exceptions;

public class ShoppingCartIsEmptyException extends RuntimeException{
    public ShoppingCartIsEmptyException(String message) {
        super(message);
    }
}
