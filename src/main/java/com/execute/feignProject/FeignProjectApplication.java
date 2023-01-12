package com.execute.feignProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class FeignProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProjectApplication.class, args);
    }

}
