package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.BookListMapper;
import by.intereson.ebookservice.mappers.BookMapper;
import by.intereson.ebookservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookListMapper bookListMapper;

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookDTO(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return bookMapper.mapToDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooksUnsortedDTO() {
        List<Book> books = bookRepository.findAll();
        return bookListMapper.toDTOList(books);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookResponse saveBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        bookRepository.save(book);
        return bookMapper.mapToDTO(book);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BookResponse updateBook(Long id, CreateBookRequest request) {
        Book newBook = bookMapper.mapToEntity(request);
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        oldBook.setBookName(newBook.getBookName());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPublishingYear(newBook.getPublishingYear());
        oldBook.setPublishingHouse(newBook.getPublishingHouse());
        oldBook.setDescription(newBook.getDescription());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setQuantity(newBook.getQuantity());
        bookRepository.save(oldBook);
        return bookMapper.mapToDTO(oldBook);
    }
}
