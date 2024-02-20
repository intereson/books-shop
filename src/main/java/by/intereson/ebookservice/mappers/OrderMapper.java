package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {OrderDetailListMapper.class})
public interface OrderMapper {
   OrderResponse mapToDto(Order order);
}
