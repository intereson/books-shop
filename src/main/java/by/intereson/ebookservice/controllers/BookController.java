package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class BookController {
    private final BookService bookService;

    /**
     * Get a book by book id
     */
    @GetMapping("books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBookByIdDto(id);
    }

    /**
     * Get all the books
     */
    @GetMapping("books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooks() {
        return bookService.getBooksUnsortedDto();
    }

    /**
     * Get all books by genre
     */
    @GetMapping("books/genre")
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getBooksByGenre(@RequestBody @Valid GetBooksByGenreRequest request) {
        return bookService.getBooksByGenre(request);
    }

    /**
     * Fill out the new book form
     */
    @PostMapping("books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody @Valid CreateBookRequest request) {
        return bookService.createBook(request);
    }

    /**
     * Update the form of a book by book id
     */
    @PutMapping("books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid CreateBookRequest request) {
        return bookService.updateBookById(id, request);
    }

    /**
     * delete a book from the store by  book id
     */
    @DeleteMapping("books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
