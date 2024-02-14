package by.intereson.ebookservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartOfTheOrderResponse {
    private Long id;
    private Double price;
    private Double sumPrice;
    private Integer quantity;
    private String bookName;

}
