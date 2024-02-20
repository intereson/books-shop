package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private BigDecimal price;
    private String dateTime;
    private Genre genre;
    private Integer quantity;
    private Integer reserveQuantity;
}
