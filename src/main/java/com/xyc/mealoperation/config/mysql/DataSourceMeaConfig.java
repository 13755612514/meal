package com.xyc.mealoperation.config.mysql;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author xiongyancong
 * @createTime 2019/12/4 10:49
 * @Description
 **/
@Configuration
@EnableTransactionManagement
public class DataSourceMeaConfig {
    @Bean(name = "mealDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.meal")
    public DataSource mealDatasource() {return DataSourceBuilder.create().build();
    }

}
