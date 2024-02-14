package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private OrderStatus orderStatus;
    private Double sumPrice;
    private List<PartOfTheOrderResponse> parts;
}
