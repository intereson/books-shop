package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface OrderListMapper {
    List<OrderResponse> mapToDto(List<Order> orders);
}
