package me.loveshare.data.configuration.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Tony on 2017/3/22.
 * MySQL数据库驱动配置信息和Druid数据库连接池(数据源)配置信息<br/>
 */
@Data
@Slf4j
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "database")
public class MySQLDruidDataSourceConfiguration {

    private DruidDataSource datasource;

    private Integer index;

    @Primary
    @Bean
    public DataSource dataSource() {
        // 数据库驱动配置信息
        datasource = new DruidDataSource();
        String url = url();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDbType(type);
        datasource.setDriverClassName(driver);

        // 数据库连接池配置信息
        datasource.setMaxWait(maxWait);
        datasource.setInitialSize(initialSize);  //配置后, junit将不能执行
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        datasource.setRemoveAbandoned(removeAbandoned);
        datasource.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
        datasource.setTimeBetweenConnectErrorMillis(timeBetweenConnectErrorMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setLogAbandoned(logAbandoned);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            log.error("Druid setting filters exception: " + e.getMessage(), e);
        }
        datasource.setConnectionProperties(connectionProperties);
        log.info("\n*** Initialize Druid datasource(MySQL) successful :\n[" + index + "] " + url);
        return datasource;
    }

    @PreDestroy
    public void close() {
        if (datasource != null) {
            datasource.close();
        }
    }

    /**
     * get database connect url.
     */
    private String url() {
        switch (index) {
            case 1: {
                return url1;
            }
            case 2: {
                return url2;
            }
            case 3: {
                return url3;
            }
            default: {
                return null;
            }
        }
    }

    @Value("${jdbc.url.1}")
    private String url1;
    @Value("${jdbc.url.2}")
    private String url2;
    @Value("${jdbc.url.3}")
    private String url3;


    @Value("${jdbc.username.1}")
    private String username;
    @Value("${jdbc.password.1}")
    private String password;
    @Value("${jdbc.driver.1}")
    private String driver;
    @Value("${jdbc.type.1}")
    private String type;

    @Value("${pool.initialSize.1}")
    private int initialSize;
    @Value("${pool.minIdle.1}")
    private int minIdle;
    @Value("${pool.maxActive.1}")
    private int maxActive;
    @Value("${pool.maxWait.1}")
    private long maxWait;
    @Value("${pool.timeBetweenEvictionRunsMillis.1}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${pool.minEvictableIdleTimeMillis.1}")
    private long minEvictableIdleTimeMillis;
    @Value("${pool.maxEvictableIdleTimeMillis.1}")
    private long maxEvictableIdleTimeMillis;
    @Value("${pool.removeAbandoned.1}")
    private boolean removeAbandoned;
    @Value("${pool.removeAbandonedTimeoutMillis.1}")
    private long removeAbandonedTimeoutMillis;
    @Value("${pool.timeBetweenConnectErrorMillis.1}")
    private long timeBetweenConnectErrorMillis;
    @Value("${pool.validationQuery.1}")
    private String validationQuery;
    @Value("${pool.testWhileIdle.1}")
    private boolean testWhileIdle;
    @Value("${pool.testOnBorrow.1}")
    private boolean testOnBorrow;
    @Value("${pool.testOnReturn.1}")
    private boolean testOnReturn;
    @Value("${pool.poolPreparedStatements.1}")
    private boolean poolPreparedStatements;
    @Value("${pool.maxOpenPreparedStatements.1}")
    private int maxOpenPreparedStatements;
    @Value("${pool.maxPoolPreparedStatementPerConnectionSize.1}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${pool.logAbandoned.1}")
    private boolean logAbandoned;
    @Value("${pool.filters.1}")
    private String filters;
    @Value("${pool.connectionProperties.1}")
    private String connectionProperties;
}
