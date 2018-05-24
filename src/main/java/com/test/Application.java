package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author wuwei
 * @date 2018/5/24 12:06
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"com.test.dao.read","com.test.dao.write"})
public class Application {

    public static void main(String[] args) {
        System.out.println("main 开始...");
        SpringApplication.run(Application.class, args);
        System.out.println("main 结束...");
    }
}
