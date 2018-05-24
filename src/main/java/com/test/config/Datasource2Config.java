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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wuwei
 * @date 2018/5/3 15:19
 */
@Configuration
@MapperScan(basePackages = {"com.test.dao.write"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class Datasource2Config {

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource setDataSource() {
        //return DataSourceBuilder.create().build();
        System.out.println("实例化datasource2");
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource,
                                                  org.apache.ibatis.session.Configuration config) throws Exception {
        System.out.println("实例化sqlSessionFactory2");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        System.out.println("实例化sqlSessionTemplate2");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
