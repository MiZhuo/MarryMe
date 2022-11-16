package site.mizhuo.marry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import site.mizhuo.marry.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//内存中
                .withClient("client")//客户端ID
                .secret(passwordEncoder.encode("111111"))//秘钥
                .redirectUris("https://www.baidu.com")//重定向到的地址
                .scopes("all")//授权范围
                .autoApprove(true)//自动授权，返回验证码
                .authorizedGrantTypes("authorization_code","password");//授权类型
    }

    /**
     * 配置授权端点
     * 密码模式必须配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //密码模式需要配置(可能是因为直接根据用户名密码登录)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl);
    }
}

