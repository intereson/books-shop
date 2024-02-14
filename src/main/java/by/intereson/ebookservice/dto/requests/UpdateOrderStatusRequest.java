package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    private OrderStatus status;
}
