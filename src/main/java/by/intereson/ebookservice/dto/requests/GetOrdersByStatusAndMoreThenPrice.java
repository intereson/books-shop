package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersByStatusAndMoreThenPrice {
    @NotNull
    private OrderStatus orderStatus;
    @PositiveOrZero
    private BigDecimal price;
}
