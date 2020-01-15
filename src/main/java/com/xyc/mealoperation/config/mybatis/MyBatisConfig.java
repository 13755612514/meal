package com.xyc.mealoperation.config.mybatis;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @Author xiongyancong
 * @createTime 2019/12/19 15:29
 * @Description
 **/
@Configuration
@MapperScan(basePackages = {"com.xyc.mealoperation.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryMeal")
public class MyBatisConfig {

    @Autowired
    @Qualifier("mealDataSource")
    DataSource windDataSource;

    @Primary
    @Bean(name = "sqlSessionFactoryMeal") public SqlSessionFactory sqlSessionFactoryMeal()
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(windDataSource);
        bean.setTypeAliasesPackage("com.xyc.mealoperation.entity");
        bean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean public SqlSessionTemplate sqlSessionTemplateWind() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryMeal());
    }
}