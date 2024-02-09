package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BookController {
    private final BookService bookService;

    @GetMapping("book/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookDTO(id);
    }

    @PostMapping("book")
    public BookResponse saveBook(@RequestBody CreateBookRequest request) {
        return bookService.saveBook(request);
    }

}
