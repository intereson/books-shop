package by.intereson.ebookservice.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartOfTheOrderRequest {
    @NotNull
    private Long idBook;
    @NotNull
    @Positive
    private Integer quantity;
    @NotNull
    private Long idShoppingCart;
}
