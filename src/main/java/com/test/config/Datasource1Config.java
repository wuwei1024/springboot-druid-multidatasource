package com.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wuwei
 * @date 2018/5/3 15:00
 */
@Configuration
@MapperScan(basePackages = {"com.test.dao.read"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class Datasource1Config {

    @Primary
    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource setDataSource() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("datasource1") DataSource dataSource,
                                                  org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
