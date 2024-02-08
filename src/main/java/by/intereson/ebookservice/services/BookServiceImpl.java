package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.mappers.BookMapper;
import by.intereson.ebookservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Book saveBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        return bookRepository.save(book);
    }
}
