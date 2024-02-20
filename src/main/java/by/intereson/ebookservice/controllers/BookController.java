package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class BookController {
    private final BookService bookService;

    /**
     * Fill out the new book form
     */
    @PostMapping(BOOKS_URL)
    @ResponseStatus(CREATED)
    public BookResponse createBook(@RequestBody @Valid CreateBookRequest request) {
        return bookService.createBook(request);
    }

    /**
     * Get a book by book id
     */
    @GetMapping(BOOKS_ID_URL)
    @ResponseStatus
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookResponse(id);
    }

    /**
     * Get all the books
     */
    @GetMapping(BOOKS_URL)
    @ResponseStatus
    public List<BookResponse> getBooks() {
        return bookService.getBooksUnsorted();
    }

    /**
     * Get all books by genre
     */
    @GetMapping(BOOKS_GENRE_URL)
    @ResponseStatus
    public List<BookResponse> getBooksByGenre(@RequestBody @Valid GetBooksByGenreRequest request) {
        return bookService.getBooksByGenre(request);
    }

    /**
     * Update the form of a book by book id
     */
    @PutMapping(BOOKS_ID_URL)
    @ResponseStatus
    public BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid CreateBookRequest request) {
        return bookService.updateBook(id, request);
    }

    /**
     * delete a book from the store by  book id
     */
    @DeleteMapping(BOOKS_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
