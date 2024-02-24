package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBooksByGenreRequest {
    @NotNull
    private Genre genre;
}
