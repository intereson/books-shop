package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {
    private Long idShoppingCart;
    private List<PartResponse> parts;
    private Double sumPrice;
}
