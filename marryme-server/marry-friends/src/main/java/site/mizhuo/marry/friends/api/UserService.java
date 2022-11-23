package site.mizhuo.marry.friends.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;

/**
 *
 * @author mizhuo
 * @date 2022/11/23
 */
@FeignClient(name = "marry-portal",path = "/sso")
public interface UserService {

    /**
     * 获取当前登陆用户信息
     * @return
     */
    @PostMapping("/info")
    public CommonResult<?> info();
}
