package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartOfTheOrderMapper {
    PartOfTheOrderResponse mapToDto(PartOfTheOrder part);

    PartOfTheOrder mapToEntity(CreatePartOfTheOrderRequest request);

}
