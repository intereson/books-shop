package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    @NotBlank
    private String bookName;
    @NotBlank
    private String author;
    @NotNull
    private Integer publishingYear;
    @NotNull
    private String publishingHouse;
    @NotNull
    private String description;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    private Genre genre;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    @PositiveOrZero
    private Integer reserveQuantity;
}
