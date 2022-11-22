package site.mizhuo.marry.friends.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * @author mizhuo
 */
@Configuration
@EnableTransactionManagement
@MapperScan("site.mizhuo.marry.friends.mapper")
public class MyBatisConfig {
}
