package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByGenre(Genre genre);
}
