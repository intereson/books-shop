package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookListMapper.class})
public interface ShoppingCartMapper {
//     ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);
    ShoppingCartResponse mapToDTO(ShoppingCart shoppingCart);

}
