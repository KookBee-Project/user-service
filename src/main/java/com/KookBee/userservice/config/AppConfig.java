package com.KookBee.userservice.config;

import com.KookBee.userservice.converter.Encrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Encrypt encrypt() {
        return new Encrypt();
    }
}
