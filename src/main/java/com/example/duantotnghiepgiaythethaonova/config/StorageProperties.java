package com.example.duantotnghiepgiaythethaonova.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("storage")
@EnableConfigurationProperties(MultipartProperties.class)
public class StorageProperties {
    private String location;
}
