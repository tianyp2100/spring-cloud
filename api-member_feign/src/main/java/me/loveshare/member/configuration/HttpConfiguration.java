package me.loveshare.member.configuration;

import io.undertow.Undertow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tony on 2017/1/14.
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "http")
public class HttpConfiguration {

    private Integer port;

    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory undertow = new UndertowEmbeddedServletContainerFactory();
        undertow.addBuilderCustomizers((Undertow.Builder builder) -> {
            builder.addHttpListener(port, "0.0.0.0");
        });
        log.info("\n*** Undertow http setting successful." + port);
        return undertow;
    }
}
