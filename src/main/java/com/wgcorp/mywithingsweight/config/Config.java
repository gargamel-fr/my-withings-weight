package com.wgcorp.mywithingsweight.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(OAuth2AuthorizedClientService clientService) {
        return new RestTemplateBuilder().interceptors((ClientHttpRequestInterceptor) (httpRequest, bytes, clientHttpRequestExecution) -> {

            OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(
                    SecurityContextHolder.getContext().getAuthentication());

            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(token.getAuthorizedClientRegistrationId(), token.getName());

            httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer: " + client.getAccessToken().getTokenValue());

            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }).build();
    }
}
