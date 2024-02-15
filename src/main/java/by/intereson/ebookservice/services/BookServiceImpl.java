package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.dto.requests.GetBooksByGenreRequest;
import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.exceptions.QuantityException;
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookResponse createBook(CreateBookRequest request) {
        Book book = bookMapper.mapToEntity(request);
        bookRepository.save(book);
        return bookMapper.mapToDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id:" + id));
    }

    @Override
    public BookResponse getBookByIdDTO(Long id) {
        Book book = getBookById(id);
        return bookMapper.mapToDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooksUnsortedDTO() {
        List<Book> books = bookRepository.findAll();
        return bookListMapper.toDTOList(books);
    }



    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BookResponse updateBookById(Long id, CreateBookRequest request) {
        Book newBook = bookMapper.mapToEntity(request);
        Book oldBook = getBookById(id);
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
        return bookMapper.mapToDTO(oldBook);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increaseInQuantityBook(Book book, Integer quantity) {
        book.setQuantity(book.getQuantity()+quantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void reduceFromQuantityBook(Book book, Integer quantity) {
        int differenceQuantity=book.getQuantity()-quantity;
        if(differenceQuantity<0){
            throw new QuantityException("There is no such amount . There is only " + book.getQuantity());
        }
        else book.setQuantity(differenceQuantity);
        bookRepository.save(book);
            }
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void reduceFromReserveQuantityBook(Book book, Integer quantity) {
        book.setReserveQuantity(book.getReserveQuantity()-quantity);
        bookRepository.save(book);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increaseInReserveQuantityBook(Book book, Integer quantity) {
                        book.setReserveQuantity(book.getReserveQuantity()+quantity);
        bookRepository.save(book);
    }
    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByGenre(GetBooksByGenreRequest request) {
        List<Book> booksByGenre = bookRepository.findBooksByGenre(request.getGenre());
        return bookListMapper.toDTOList(booksByGenre);
    }
}
