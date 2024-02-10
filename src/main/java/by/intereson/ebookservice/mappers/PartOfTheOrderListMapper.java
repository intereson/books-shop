package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.PartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PartOfTheOrderMapper.class)
public interface PartOfTheOrderListMapper {
    List<PartResponse> toDTOList(List<PartOfTheOrder> parts);
}
