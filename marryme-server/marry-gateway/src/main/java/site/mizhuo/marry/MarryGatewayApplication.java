package site.mizhuo.marry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mizhuo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class MarryGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryGatewayApplication.class, args);
    }

}
