package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.BookDTO;
import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.mappers.BookMapper;
import by.intereson.ebookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("book/{id}")
    public BookDTO getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return bookMapper.mapToDTO(book);
    }

    @PostMapping("book")
    public BookDTO saveUser(@RequestBody CreateBookRequest request) {
        Book book = bookService.saveBook(request);
        return BookMapper.INSTANCE.mapToDTO(book);
    }

}
