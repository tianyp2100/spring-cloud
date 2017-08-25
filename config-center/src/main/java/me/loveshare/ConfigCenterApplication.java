package me.loveshare;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Tony on 2017/3/7.<br/>
 * 通过@EnableConfigServer注解激活配置服务.<br/>
 * 说明：
 * 在application.yml中有个git.uri的配置，目前配置的是 https://github.com/typa1/microservices-configs.git
 * 获取git上的资源信息遵循如下规则：
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * 例如本例：可使用以下路径来访问config-v1-dev.properties：
 * http://192.168.1.119:1102/master/config-v1-dev.properties
 * http://192.168.1.119:1102/master/config-v1-dev
 * http://192.168.1.119:1102/config-v1/dev/master
 * 注:
 * post方式：执行http://192.168.1.119:1102/refresh 会刷新env中的配置
 */
@Slf4j
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
        log.info("\n--------------------------------Spring cloud microservices config center start successful.--------------------------------\n");
    }
}
