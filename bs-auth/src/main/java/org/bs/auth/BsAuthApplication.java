package org.bs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :wkh
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"org.bs"})
@EnableFeignClients(basePackages = "org.bs")
public class BsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsAuthApplication.class, args);
    }
}
