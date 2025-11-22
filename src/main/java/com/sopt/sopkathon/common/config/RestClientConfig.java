package com.sopt.sopkathon.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
            .baseUrl("https://api.openuv.io")
            .build();
    }

}

