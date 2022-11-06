package site.mizhuo.marry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mizhuo
 */
@SpringBootApplication
@MapperScan("site.mizhuo.marryfriends.mapper")
public class MarryFriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryFriendsApplication.class, args);
    }

}
