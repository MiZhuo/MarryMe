package site.mizhuo.marry.portal.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.mizhuo.marry.common.CommonResult;

import java.util.Map;

/**
 * 认证服务远程调用
 * @author mizhuo
 */
@FeignClient(name = "marry-auth",path = "/oauth")
public interface AuthApi {

    /**
     * 获取token
     * @param parameters 用户信息
     * @return token
     */
    @PostMapping(value = "/token")
    CommonResult<?> getAccessToken(@RequestParam Map<String, String> parameters);

}
