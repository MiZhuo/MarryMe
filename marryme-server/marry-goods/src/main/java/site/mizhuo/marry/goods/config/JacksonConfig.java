package site.mizhuo.marry.goods.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import site.mizhuo.marry.config.BaseJacksonConfig;


/**
 * Jackson相关配置类
 * json不返回null的字段
 * @author mizhuo
 */
@Configuration
public class JacksonConfig extends BaseJacksonConfig {

}