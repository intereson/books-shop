package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBooksByGenreRequest {
    private Genre genre;
}
