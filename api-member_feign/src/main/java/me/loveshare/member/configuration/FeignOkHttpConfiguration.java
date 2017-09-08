package me.loveshare.member.configuration;

import feign.Feign;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tony on 2017/9/8.<br/>
 * Default JDK URLConnection ==> Apache HttpClient ==> OkHttp HttpClient .<br/>
 * Feign在默认情况下使用的是JDK原生的URLConnection发送HTTP请求，没有连接池，但是对每个地址会保持一个长连接，即利用HTTP的persistence connection。<br/>
 * 用Apache的HTTP Client替换Feign原始的http client, 从而获取连接池、超时时间等与性能息息相关的控制能力。<br/>
 * Spring Cloud从Brixtion.SR5版本开始支持这种替换。<br/>
 * 此处OKHttpClient替代ApacheHttpClient：<br/>
 * 1.支持SPDY, 可以合并多个到同一个主机的请求；<br/>
 * 2.使用连接池技术减少请求的延迟(如果SPDY是可用的话)；<br/>
 * 3.使用GZIP压缩减少传输的数据量；<br/>
 * 4.缓存响应避免重复的网络请求。
 */
@Data
@Slf4j
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@ConfigurationProperties(prefix = "okhttp")
public class FeignOkHttpConfiguration {

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES))
                // .addInterceptor();
                .build();
        log.info("\n*** Initialize OkHttpClient successful." + connectTimeout);
        return client;
    }

    private Long connectTimeout;
    private Long readTimeout;
    private Long writeTimeout;
    private Integer maxIdleConnections;
    private Long keepAliveDuration;
}
