package org.bs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author :wkh
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"org.bs"})
public class BsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsAuthApplication.class, args);
    }
}
