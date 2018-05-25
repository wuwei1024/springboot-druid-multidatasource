package com.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author wuwei
 * @date 2018/5/3 15:19
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.test.dao.write"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class Datasource2Config {

    @Value("${mybatis.configuration.mapUnderscoreToCamelCase}")
    private boolean mapUnderscoreToCamelCase;

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * datasource配置
     *
     * @return
     */
    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource setDataSource() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    /**
     * SqlSessionFactory配置
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //设置数据源
        bean.setDataSource(dataSource);
        //设置驼峰转换
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        bean.setConfiguration(configuration);
        //设置mapper文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        //给包中的类注册别名，注册后可以直接使用类名，而不用使用全限定的类名
        bean.setTypeAliasesPackage(typeAliasesPackage);
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate配置
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "datasource2TransactionManager")
    public DataSourceTransactionManager clusterTransactionManager(@Qualifier("dataSource2") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
