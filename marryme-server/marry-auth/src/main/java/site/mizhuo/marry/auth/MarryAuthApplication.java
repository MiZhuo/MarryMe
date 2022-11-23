package site.mizhuo.marry.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mizhuo
 */
@EnableFeignClients(basePackages = "site.mizhuo.marry.auth.api")
@EnableDiscoveryClient
@SpringBootApplication
public class MarryAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryAuthApplication.class, args);
    }

}
