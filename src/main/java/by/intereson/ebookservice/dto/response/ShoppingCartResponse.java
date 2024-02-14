package by.intereson.ebookservice.dto.response;

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
    private List<PartOfTheOrderResponse> parts;
    private Double sumPrice;
}
