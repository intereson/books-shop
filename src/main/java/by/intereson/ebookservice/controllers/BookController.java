package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BookController {
    private final BookService bookService;

    @GetMapping("books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookDTO(id);
    }

    @GetMapping("books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooksUnsorted() {
        return bookService.getAllBooksUnsortedDTO();
    }

    @PostMapping("books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody CreateBookRequest request) {
        return bookService.createBook(request);
    }

    @PutMapping("books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateBook(@PathVariable Long id, @RequestBody CreateBookRequest request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
