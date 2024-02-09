package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.response.ErrorResponse;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
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
                .error("По вашему запросу в базу данных произошла ошибка")
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
