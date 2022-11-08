package site.mizhuo.marry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mizhuo
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MarryPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryPortalApplication.class, args);
    }

}
