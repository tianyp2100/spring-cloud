package me.loveshare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@Slf4j
@SpringBootApplication(scanBasePackages = "me.loveshare")
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class ApiMemberFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMemberFeignApplication.class, args);
        log.info("\n--------------------------------Microservices [* member feign api *] start successful.--------------------------------\n");
    }
}
