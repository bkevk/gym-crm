package com.bk.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bk.gym.repository")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.bk.gym.feign")
public class GymApp {
    public static void main(String[] args){
        SpringApplication.run(GymApp.class, args);
    }
}
