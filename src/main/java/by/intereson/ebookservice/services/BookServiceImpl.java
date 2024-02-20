package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.dto.response.OpenLibraryResponse;
import by.intereson.ebookservice.dto.response.OpenLibraryRootResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.gateways.OpenLibraryGateway;
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
    private final OpenLibraryGateway openLibraryGateway;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public BookResponse createBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        setFirstPublishYearFromOpenLibrary(book);
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
        Book book = bookMapper.mapToEntity(request);
        getUpdatedBook(bookId, request, book);
        bookRepository.save(book);
        return bookMapper.mapToDto(book);
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

    private void setFirstPublishYearFromOpenLibrary(Book book) {
        openLibraryGateway.setRequest(book.getBookName());
        OpenLibraryRootResponse bookInfo = openLibraryGateway.getBookInfo();
        OpenLibraryResponse openLibraryResponse = bookInfo.getDocs().stream().findFirst().orElseThrow();
        Integer firstPublishYear = openLibraryResponse.getFirst_publish_year();
        book.setFirstPublishYear(firstPublishYear);
    }

    private void getUpdatedBook(Long bookId, CreateBookRequest request, Book book) {
        book.setId(bookId);
        book.setBookName(book.getBookName());
        book.setAuthor(book.getAuthor());
        book.setPublishingYear(book.getPublishingYear());
        book.setPublishingHouse(book.getPublishingHouse());
        book.setDescription(book.getDescription());
        book.setGenre(book.getGenre());
        book.setPrice(book.getPrice());
        book.setQuantity(book.getQuantity());
        book.setReserveQuantity(request.getReserveQuantity());
    }
}
