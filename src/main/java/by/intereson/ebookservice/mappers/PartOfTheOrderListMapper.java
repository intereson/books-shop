package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PartOfTheOrderMapper.class)
public interface PartOfTheOrderListMapper {
    List<PartOfTheOrderResponse> mapListToDto(List<PartOfTheOrder> parts);
}
