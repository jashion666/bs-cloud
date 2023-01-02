package org.bs.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统模块
 *
 * @author ruoyi
 */
@SpringBootApplication(scanBasePackages = {"org.bs"})
@EnableFeignClients
@MapperScan("org.bs.system.mapper")
public class BsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsSystemApplication.class, args);
    }
}
