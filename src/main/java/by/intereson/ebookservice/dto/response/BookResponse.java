package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String bookName;
    private String author;
    private Integer publishingYear;
    private String publishingHouse;
    private String description;
    private Double price;
    private String dateTime;
    private Genre genre;
    private Integer quantity;
    private Integer reserveQuantity;
}
