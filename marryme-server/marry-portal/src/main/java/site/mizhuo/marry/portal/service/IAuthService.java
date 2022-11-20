package site.mizhuo.marry.portal.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.mizhuo.marry.api.CommonResult;

import java.util.Map;

/**
 * 认证服务远程调用
 *
 * @author macro
 * @date 2020/7/19
 */
@FeignClient("marry-auth")
public interface IAuthService {

    /**
     * 获取token
     * @param parameters 用户信息
     * @return token
     */
    @PostMapping(value = "/oauth/token")
    CommonResult<?> getAccessToken(@RequestParam Map<String, String> parameters);

}
