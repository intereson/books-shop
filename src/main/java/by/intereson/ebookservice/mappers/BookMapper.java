package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.BookResponse;
import by.intereson.ebookservice.dto.requests.CreateBookRequest;
import by.intereson.ebookservice.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE= Mappers.getMapper(BookMapper.class);

    BookResponse mapToDTO(Book book);
    Book mapToEntity(CreateBookRequest request);
}
