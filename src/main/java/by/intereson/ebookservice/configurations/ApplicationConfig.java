package by.intereson.ebookservice.configurations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

import static by.intereson.ebookservice.utils.Constants.*;

@Configuration
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(value = OPEN_LIBRARY_PROPERTY_URL)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(BASE_NAME_MESSAGE);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING_MESSAGE);
        return messageSource;
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
