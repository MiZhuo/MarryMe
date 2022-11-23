package site.mizhuo.marry.friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mizhuo
 */
@EnableFeignClients(basePackages = "site.mizhuo.marry.friends.api")
@EnableDiscoveryClient
@SpringBootApplication
public class MarryFriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryFriendsApplication.class, args);
    }

}
