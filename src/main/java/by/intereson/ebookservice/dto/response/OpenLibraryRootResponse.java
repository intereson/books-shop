package by.intereson.ebookservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenLibraryRootResponse {
    private Integer numFound;
    private Integer start;
    private boolean numFoundExact;
    private List<OpenLibraryResponse> docs;
    private Integer num_found;
    private String q;
    private Object offset;
}
