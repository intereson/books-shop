package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.entities.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface BookListMapper {
    List<BookResponse> mapListToDto(List<Book> bookList);
}
