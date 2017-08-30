package me.loveshare.monitor.center;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by Tony on 2017/6/28.
 * -----------------------断路器------------------------------------
 * 测试步骤:
 * 1. 访问: http://192.168.1.119:1303/hystrix.stream 可以查看Dashboard
 * 2. 在上面的输入框填入: http://想监控的服务的ip:端口/hystrix.stream进行测试
 * 注意：首先要先调用一下想监控的服务的API，否则将会显示一个空的图表.
 * <p>
 * 断路器: Hystrix 仪表盘(Circuit Breaker: Hystrix Dashboard):访问几次就会出现!
 * http://192.168.1.119:1303/hystrix.stream
 * 输入框中输入:
 * http://192.168.1.151:1301/hystrix.stream
 * http://192.168.1.151:1301/hystrix.stream
 * http://192.168.1.119:1303/hystrix/monitor?stream=http%3A%2F%2F192.168.1.151%3A1301%2Fhystrix.stream
 * -----------------------健康指标------------------------------------
 * 健康指标(Health Indicator):
 * http://192.168.1.119:1303/health
 * -----------------------------------------------------------------
 *
 * @EnableTurbine 接口，激活对Turbine的支持
 * http://192.168.1.119:1303/turbine.stream
 */
@Slf4j
@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class MonitorCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorCenterApplication.class, args);
        log.info("\n--------------------------------Spring cloud microservices monitor center start successful.--------------------------------\n");
    }
}
