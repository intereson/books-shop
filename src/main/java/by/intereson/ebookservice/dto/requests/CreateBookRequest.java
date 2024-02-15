package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    private String bookName;
    private String author;
    private Integer publishingYear;
    private String publishingHouse;
    private String description;
    private Double price;
    private Genre genre;
    private Integer quantity;
    private Integer reserveQuantity;
}
