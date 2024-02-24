package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.CreateOrderDetailRequest;
import by.intereson.ebookservice.dto.response.OrderDetailResponse;
import by.intereson.ebookservice.entities.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailResponse mapToDto(OrderDetail part);
    OrderDetail mapToEntity(CreateOrderDetailRequest request);

}
