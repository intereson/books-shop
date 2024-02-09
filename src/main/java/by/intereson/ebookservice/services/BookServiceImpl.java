package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.BookMapper;
import by.intereson.ebookservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponse getBookDTO(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return bookMapper.mapToDTO(book);
    }

    @Override
    public Book getBook(Long id) {
        return  bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));

    }

    @Override
    public BookResponse saveBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        bookRepository.save(book);
        return bookMapper.mapToDTO(book);
    }
}
