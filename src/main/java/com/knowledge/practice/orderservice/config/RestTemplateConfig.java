package com.knowledge.practice.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        var restTemplateFactory =new SimpleClientHttpRequestFactory();
        restTemplateFactory.setConnectTimeout(2000);
        restTemplateFactory.setReadTimeout(2000);
        return new RestTemplate(restTemplateFactory);
    }
}
