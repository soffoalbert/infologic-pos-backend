package com.sofo.sale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sofo on 2017/09/24.
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
