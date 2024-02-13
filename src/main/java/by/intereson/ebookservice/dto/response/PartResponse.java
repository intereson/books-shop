package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartResponse {
    private Long id;
    private Double price;
    private Double sumPrice;
    private Integer quantity;
    private String bookName;

}
