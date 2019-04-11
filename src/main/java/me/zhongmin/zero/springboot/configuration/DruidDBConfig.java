package me.zhongmin.zero.springboot.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author
 * 数据库连接属性配置 
 */

@Slf4j
public class DruidDBConfig {

//
//
//    public DataSource dataSource(Environment env) {
//        return getDruidDataSource(env);
//    }
//
////    @Bean(name = "secondaryDataSource")
////    @Qualifier("secondaryDataSource")
////    @Primary
//    public DataSource secondaryDataSource(Environment env) {
//        return getDruidDataSource(env);
//    }
    @Bean(name = "dataSource")
    @Qualifier("dataSource")
    public DataSource getDruidDataSource(Environment env) {
        HikariDataSource datasource = new HikariDataSource();

        datasource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        datasource.setUsername(env.getProperty("spring.datasource.username"));
        datasource.setPassword(env.getProperty("spring.datasource.password"));
        datasource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

        //configuration
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//            log.error("druid configuration initialization filter : {0}", e);
//        }
//        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
}  