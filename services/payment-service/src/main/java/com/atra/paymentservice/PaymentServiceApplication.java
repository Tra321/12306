package com.atra.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 支付服务应用启动类
 *
 * @author Cline
 */
@SpringBootApplication // 标记这是一个 Spring Boot 应用
public class PaymentServiceApplication {

    /**
     * Spring Boot 应用的主入口点。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
