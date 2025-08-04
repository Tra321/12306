package com.atra.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务应用启动类
 *
 * @author Cline
 */
@SpringBootApplication // 标记这是一个 Spring Boot 应用，它整合了 @Configuration, @EnableAutoConfiguration, 和 @ComponentScan
public class UserServiceApplication {

    /**
     * Spring Boot 应用的主入口点。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // SpringApplication.run() 方法会启动整个 Spring Boot 应用
        // 它会创建一个 Spring 应用上下文 (ApplicationContext) 并加载所有的 bean
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
