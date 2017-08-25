package me.loveshare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Tony on 2017/3/6.<br>
 * 微服务注册中心.<br>
 * 通过@EnableEurekaServer注解激活注册服务.<br/>
 * 注：若查看更多信息，设置${third.party.log.level}参数=INFO.<br>
 * Web监控地址:  http://192.168.1.119:1101/
 * Eureka服务注册发现地址: http://192.168.1.119:1101/eureka/
 */
@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class RegistryCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryCenterApplication.class, args);
        log.info("\n--------------------------------Spring cloud microservices eureka registry center start successful.--------------------------------\n");
    }
}
