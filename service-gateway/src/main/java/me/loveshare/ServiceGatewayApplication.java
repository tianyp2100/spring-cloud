package me.loveshare;

import lombok.extern.slf4j.Slf4j;
import me.loveshare.gateway.gateway.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by Tony on 2017/3/10.
 *
 * @EnableZuulProxy注解激活zuul。 该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置。
 */
@Slf4j
@EnableZuulProxy
@SpringCloudApplication
public class ServiceGatewayApplication {

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    // http://192.168.1.119:1304/api-a/member/list2.json   //log: access token is empty
    // http://192.168.1.119:1304/api-a/member/list2.json?accessToken=token   //log: access token ok
    // http://192.168.1.119:1304/api-a/member/list2.json?code=KWY987654&name=%E5%8D%A1%E5%93%87%E4%BC%8A&id=123&accessToken=token  //加载的数据

    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
        log.info("\n--------------------------------Spring cloud microservices gateway start successful.--------------------------------\n");
    }
}
