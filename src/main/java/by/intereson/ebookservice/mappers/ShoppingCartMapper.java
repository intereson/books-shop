package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PartOfTheOrderListMapper.class})
public interface ShoppingCartMapper {

    ShoppingCartResponse mapToDTO(ShoppingCart shoppingCart);

}
