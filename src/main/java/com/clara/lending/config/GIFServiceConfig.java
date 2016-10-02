package com.clara.lending.config;

import com.clara.lending.client.GiphyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GIFServiceConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public GiphyClient giphyClient(){
        return new GiphyClient();
    }
}
