package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersByStatus {
    @NotNull
    private OrderStatus orderStatus;
}
