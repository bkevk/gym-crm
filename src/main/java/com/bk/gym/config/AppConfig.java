package com.bk.gym.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.bk.gym")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
