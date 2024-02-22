package by.intereson.ebookservice.configurations;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import static by.intereson.ebookservice.utils.Constants.OPEN_LIBRARY_PROPERTY;

@Data
@Validated
@Configuration
@ConfigurationProperties(value = OPEN_LIBRARY_PROPERTY)
public class OpenLibraryGatewayProperties {
    private @NotBlank String url;
}
