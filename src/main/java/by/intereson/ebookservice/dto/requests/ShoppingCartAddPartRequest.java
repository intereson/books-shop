package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.entities.PartOfTheOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartAddPartRequest {
    private Long idPartOfTheOrder;

}
