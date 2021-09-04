package com.banking.account.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
//@ConfigurationProperties(prefix = "banking.account")
@Getter
@Setter
public class ServiceConfig {
    private String property;
    @Value("${redis.server}")
    private String redisServer = "";

    @Value("${redis.port}")
    private String redisPort = "";

    /* @Value("${api.common.version}")
     String apiVersion;
     @Value("${api.common.title}")
     String apiTitle;
     @Value("${api.common.description}")
     String apiDescription;
     @Value("${api.common.termsOfService}")
     String apiTermsOfService;
     @Value("${api.common.license}")
     String apiLicense;
     @Value("${api.common.licenseUrl}")
     String apiLicenseUrl;
     @Value("${api.common.externalDocDesc}")
     String apiExternalDocDesc;
     @Value("${api.common.externalDocUrl}")
     String apiExternalDocUrl;
     @Value("${api.common.contact.name}")
     String apiContactName;
     @Value("${api.common.contact.url}")
     String apiContactUrl;
     @Value("${api.common.contact.email}")
     String apiContactEmail;

     @Bean
     public OpenAPI getOpenApiDocumentation() {
         return new OpenAPI()
                 .info(new Info().title(apiTitle)
                         .description(apiDescription)
                         .version(apiVersion)
                         .contact(new Contact()
                                 .name(apiContactName)
                                 .url(apiContactUrl)
                                 .email(apiContactEmail))
                         .termsOfService(apiTermsOfService)
                         .license(new License()
                                 .name(apiLicense)
                                 .url(apiLicenseUrl)))
                 .externalDocs(new ExternalDocumentation()
                         .description(apiExternalDocDesc)
                         .url(apiExternalDocUrl));
     }
 */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.UK);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource =
                new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        String hostname = getRedisServer();
        int port = Integer.parseInt(getRedisPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
