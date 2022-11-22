package site.mizhuo.marry.goods.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * @author mizhuo
 */
@Configuration
@EnableTransactionManagement
@MapperScan("site.mizhuo.marry.goods.mapper")
public class MyBatisConfig {
}
