package by.intereson.ebookservice.configurations;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(value = "services.external.library")
public class OpenLibraryGatewayProperties {
    private @NotBlank String url;
}
