package by.intereson.ebookservice.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPartOfTheOrderInShoppingCartRequest {
    @NotNull
    private Long idPartOfTheOrder;

}
