package by.intereson.ebookservice.gateways;

import by.intereson.ebookservice.configurations.OpenLibraryGatewayProperties;
import by.intereson.ebookservice.dto.response.OpenLibraryRootResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static by.intereson.ebookservice.utils.Constants.SEARCH_REQUEST_PARAMETERS_URL;
import static by.intereson.ebookservice.utils.Constants.SEARCH_REQUEST_URL;

@Data
@Component
@RequiredArgsConstructor
@ConditionalOnBean(RestTemplate.class)
public class OpenLibraryGateway {
    private final RestTemplate restTemplate;
    private final OpenLibraryGatewayProperties openLibraryGatewayProperties;
    private String bookNameRequest;

    public OpenLibraryRootResponse getBookInfo() {
        ResponseEntity<OpenLibraryRootResponse> response = restTemplate.getForEntity(buildRequestUrl(), OpenLibraryRootResponse.class);
        return response.getBody();
    }

    private String buildRequestUrl() {
        String url = openLibraryGatewayProperties.getUrl();
        return url + SEARCH_REQUEST_URL + bookNameRequest + SEARCH_REQUEST_PARAMETERS_URL;
    }
}
