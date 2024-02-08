package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.entities.Book;

public interface BookService {
    Book getBook(Long id);
    Book saveBook(CreateBookRequest request);
}
