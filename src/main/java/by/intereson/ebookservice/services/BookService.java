package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;

import java.util.List;

public interface BookService {
    BookResponse getBookDTO(Long id);
    List<BookResponse> getAllBooksUnsortedDTO();
    Book getBook(Long id);
    BookResponse createBook(CreateBookRequest request);
    void deleteBook(Long id);
    BookResponse updateBook(Long id, CreateBookRequest request);
}
