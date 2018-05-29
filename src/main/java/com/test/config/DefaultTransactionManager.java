package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author wuwei
 * @date 2018/5/29 11:12
 */
@Configuration
public class DefaultTransactionManager implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("transactionManager2")
    private PlatformTransactionManager transactionManager2;

    /**
     * 配置默认的事物管理器
     *
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager2;
    }
}
