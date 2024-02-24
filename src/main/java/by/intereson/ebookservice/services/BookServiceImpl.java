package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.requests.UpdateBookRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.dto.response.OpenLibraryResponse;
import by.intereson.ebookservice.dto.response.OpenLibraryRootResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.OpenLibraryException;
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

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookListMapper bookListMapper;
    private final OpenLibraryGateway openLibraryGateway;

    @Override
    @Transactional
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
    public BookResponse updateBook(Long bookId, UpdateBookRequest request) {
        Book book = getBook(bookId);
        updateBook(request, book);
        return bookMapper.mapToDto(book);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void increaseInQuantityBook(Book book, Integer quantity) {
        book.setQuantity(book.getQuantity() + quantity);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void reduceFromQuantityBook(Book book, Integer quantity) {
        if (quantity > book.getQuantity()) {
            throw new QuantityException(book.getQuantity());
        }
        book.setQuantity(book.getQuantity() - quantity);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void reduceFromReserveQuantityBook(Book book, Integer quantity) {
        book.setReserveQuantity(book.getReserveQuantity() - quantity);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void increaseInReserveQuantityBook(Book book, Integer quantity) {
        book.setReserveQuantity(book.getReserveQuantity() + quantity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByGenre(GetBooksByGenreRequest request) {
        List<Book> booksByGenre = bookRepository.findBooksByGenre(request.getGenre());
        return bookListMapper.mapListToDto(booksByGenre);
    }

    private void setFirstPublishYearFromOpenLibrary(Book book) {
        openLibraryGateway.setBookNameRequest(book.getBookName());
        OpenLibraryRootResponse bookInfo = openLibraryGateway.getBookInfo();
        OpenLibraryResponse openLibraryResponse = bookInfo.getDocs().stream().findFirst()
                .orElseThrow(() -> new OpenLibraryException(book.getBookName()));
        Integer firstPublishYear = openLibraryResponse.getFirst_publish_year();
        book.setFirstPublishYear(firstPublishYear);
    }

    private void updateBook(UpdateBookRequest request, Book book) {
        book.setAuthor(request.getAuthor());
        book.setPublishingYear(request.getPublishingYear());
        book.setPublishingHouse(request.getPublishingHouse());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setReserveQuantity(request.getReserveQuantity());
    }
}
