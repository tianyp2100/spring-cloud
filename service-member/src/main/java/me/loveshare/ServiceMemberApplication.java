package me.loveshare;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication(scanBasePackages = "me.loveshare")
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan(basePackages = {"me.loveshare.service.member.data.dao"})
public class ServiceMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMemberApplication.class, args);
        log.info("\n--------------------------------Microservices [* member service *] start successful.--------------------------------\n");
    }
}
