package site.mizhuo.marry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MarryOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(MarryOauth2Application.class, args);
    }

}
