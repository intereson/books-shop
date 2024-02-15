package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;

import java.util.List;

public interface BookService {
    BookResponse createBook(CreateBookRequest request);

    Book getBookById(Long id);

    BookResponse getBookByIdDTO(Long id);

    List<BookResponse> getAllBooksUnsortedDTO();
    List<BookResponse> getBooksByGenre(GetBooksByGenreRequest request);

    BookResponse updateBookById(Long id, CreateBookRequest request);

    void deleteBookById(Long id);
    void reduceFromQuantityBook(Book book, Integer quantity);
    void increaseInQuantityBook(Book book, Integer quantity);
    void reduceFromReserveQuantityBook(Book book, Integer quantity);
    void increaseInReserveQuantityBook(Book book, Integer quantity);
}
