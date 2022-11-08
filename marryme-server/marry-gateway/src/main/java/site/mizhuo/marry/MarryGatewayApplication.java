package site.mizhuo.marry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author mizhuo
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MarryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryGatewayApplication.class, args);
    }

}
