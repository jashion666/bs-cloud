package org.bs.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 *
 * @author wkh
 */
@EnableAdminServer
@SpringBootApplication
public class BsMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsMonitorApplication.class, args);
    }
}
