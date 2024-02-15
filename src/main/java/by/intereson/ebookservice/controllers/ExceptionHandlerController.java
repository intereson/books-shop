package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.response.ErrorResponse;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.exceptions.ShoppingCartIsEmptyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error("Exception:{}", exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .error("An error has occurred in your database query! The resource was not found!")
                .localDateTime(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ShoppingCartIsEmptyException.class)
    public ErrorResponse handleShoppingCartIsEmptyException(ShoppingCartIsEmptyException exception) {
        log.error("Exception:{}", exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .error("Shopping Cart Is Empty!")
                .localDateTime(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuantityException.class)
    public ErrorResponse handleQuantityException(QuantityException exception) {
        log.error("Exception:{}", exception.getMessage());
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .error("Error quantity!")
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
