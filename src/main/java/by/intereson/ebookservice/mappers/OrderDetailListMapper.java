package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.OrderDetailResponse;
import by.intereson.ebookservice.entities.OrderDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderDetailMapper.class)
public interface OrderDetailListMapper {
    List<OrderDetailResponse> mapListToDto(List<OrderDetail> parts);
}
