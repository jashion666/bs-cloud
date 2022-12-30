package org.bs.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"org.bs"})
public class BsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsGatewayApplication.class, args);
    }

}
