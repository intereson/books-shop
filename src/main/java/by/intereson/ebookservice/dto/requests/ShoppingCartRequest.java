package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartRequest {
    private Long idBook;
}
