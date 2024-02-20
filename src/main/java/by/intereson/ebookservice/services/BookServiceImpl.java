package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.mappers.BookListMapper;
import by.intereson.ebookservice.mappers.BookMapper;
import by.intereson.ebookservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.START_INTEGER_NULL_INDEX;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookListMapper bookListMapper;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public BookResponse createBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(bookId));
    }

    @Override
    public BookResponse getBookResponse(Long bookId) {
        Book book = getBook(bookId);
        return bookMapper.mapToDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksUnsorted() {
        List<Book> books = bookRepository.findAll();
        return bookListMapper.mapListToDto(books);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public BookResponse updateBook(Long bookId, CreateBookRequest request) {
        Book newBook = bookMapper.mapToEntity(request);
        Book oldBook = getBook(bookId);
        oldBook.setBookName(newBook.getBookName());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPublishingYear(newBook.getPublishingYear());
        oldBook.setPublishingHouse(newBook.getPublishingHouse());
        oldBook.setDescription(newBook.getDescription());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setQuantity(newBook.getQuantity());
        oldBook.setReserveQuantity(request.getReserveQuantity());
        bookRepository.save(oldBook);
        return bookMapper.mapToDto(oldBook);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void increaseInQuantityBook(Book book, Integer quantity) {
        book.setQuantity(book.getQuantity() + quantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void reduceFromQuantityBook(Book book, Integer quantity) {
        int differenceQuantity = book.getQuantity() - quantity;
        if (differenceQuantity < START_INTEGER_NULL_INDEX) {
            throw new QuantityException(book.getQuantity());
        } else book.setQuantity(differenceQuantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void reduceFromReserveQuantityBook(Book book, Integer quantity) {
        book.setReserveQuantity(book.getReserveQuantity() - quantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void increaseInReserveQuantityBook(Book book, Integer quantity) {
        book.setReserveQuantity(book.getReserveQuantity() + quantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByGenre(GetBooksByGenreRequest request) {
        List<Book> booksByGenre = bookRepository.findBooksByGenre(request.getGenre());
        return bookListMapper.mapListToDto(booksByGenre);
    }
}
