package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.response.ErrorResponse;
import by.intereson.ebookservice.exceptions.OpenLibraryException;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.exceptions.ShoppingCartIsEmptyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.intereson.ebookservice.utils.Constants.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {
    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(EntityNotFoundException exception) {
        log.error(HANDLER_EXCEPTION_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(messageSource.getMessage(RESOURCE_NOT_FOUND_KEY,
                        new Object[]{exception.getMessage(),
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))},
                        DEFAULT_RESOURCE_NOT_FOUND_MESSAGE,
                        LocaleContextHolder.getLocale()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ShoppingCartIsEmptyException.class)
    public ErrorResponse handleShoppingCartIsEmptyException(ShoppingCartIsEmptyException exception) {
        log.error(HANDLER_EXCEPTION_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(messageSource.getMessage(SHOPPING_CART_IS_EMPTY_KEY,
                        new Object[]{exception.getMessage()},
                        DEFAULT_SHOPPING_CART_IS_EMPTY_MESSAGE,
                        LocaleContextHolder.getLocale()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuantityException.class)
    public ErrorResponse handleQuantityException(QuantityException exception) {
        log.error(HANDLER_EXCEPTION_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(messageSource.getMessage(QUANTITY_FAIL_KEY,
                        new Object[]{exception.getMessage()},
                        DEFAULT_QUANTITY_FAIL_MESSAGE,
                        LocaleContextHolder.getLocale()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OpenLibraryException.class)
    public ErrorResponse handleOpenLibraryException(OpenLibraryException exception) {
        log.error(HANDLER_EXCEPTION_PATTERN, exception.getMessage());
        return ErrorResponse.builder()
                .message(messageSource.getMessage(OPEN_LIBRARY_FAIL_KEY,
                        new Object[]{exception.getMessage()},
                        OPEN_LIBRARY_FAIL_MESSAGE,
                        LocaleContextHolder.getLocale()))
                .build();
    }
}
