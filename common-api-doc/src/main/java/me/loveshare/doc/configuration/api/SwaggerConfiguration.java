package me.loveshare.doc.configuration.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Tony on 2017/2/11.<br/>
 * Swagger2提供了接口页面文档化和强大的页面测试功能来调试每个RESTful API.<br/>
 * 查看: http://yourdomain/swagger-ui.html
 */
@Data
@Slf4j
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfiguration {

    @Bean
    @Primary
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(api))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        log.info("\n*** Initialize Swagger RESTful API successful." + version);
        return new ApiInfoBuilder()
                .title(title)
                .description(description())
                .version(version)
                .build();
    }

    private String description() {
        StringBuilder su = new StringBuilder("温馨提示：");
        su.append(description).append("，作者：").append("创建时间：").append(gmt).append("(●'◡'●)。");
        return su.toString();
    }

    private String api;
    private String title;
    private String description;
    private String version;
    private String author;
    private String gmt;
}
