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

import static by.intereson.ebookservice.utils.Constants.MIN_QUANTITY_BOOK;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
    @NotBlank
    private String author;

    @NotNull
    private Integer publishingYear;

    @NotBlank
    private String publishingHouse;

    @NotBlank
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Genre genre;

    @NotNull
    @Min(1)
    private Integer quantity;

    @PositiveOrZero
    private Integer reserveQuantity;
}
