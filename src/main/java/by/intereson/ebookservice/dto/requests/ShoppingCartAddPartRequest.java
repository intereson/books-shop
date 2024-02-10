package by.intereson.ebookservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartAddPartRequest {
    private Long idBook;
    private Integer quantity;
}
