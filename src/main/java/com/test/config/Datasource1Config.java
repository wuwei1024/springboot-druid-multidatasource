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
@Primary
@Configuration
@MapperScan(basePackages = {"com.test.dao.read"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class Datasource1Config {

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource setDataSource() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("datasource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //设置数据源
        bean.setDataSource(dataSource);
        //设置驼峰转换
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        //设置mapper文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //给包中的类注册别名，注册后可以直接使用类名，而不用使用全限定的类名
        bean.setTypeAliasesPackage("com.test.entity");
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
