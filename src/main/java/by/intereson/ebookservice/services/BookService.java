package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;

public interface BookService {
    BookResponse getBookDTO(Long id);
    Book getBook(Long id);
    BookResponse saveBook(CreateBookRequest request);
}
