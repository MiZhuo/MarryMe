package site.mizhuo.marry.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis相关配置
 *
 * @author macro
 * @date 2020/3/2
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
