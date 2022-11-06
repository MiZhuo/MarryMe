package site.mizhuo.marry.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 *
 * @author macro
 * @date 2019/4/8
 */
@Configuration
@EnableTransactionManagement
@MapperScan("site.mizhuo.marry.mapper")
public class MyBatisConfig {
}
